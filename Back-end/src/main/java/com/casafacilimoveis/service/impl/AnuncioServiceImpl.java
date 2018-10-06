package com.casafacilimoveis.service.impl;

import com.casafacilimoveis.model.beans.ResponseError;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Imagem;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.CodeError;
import com.casafacilimoveis.model.enums.TipoNegocio;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.repository.UsuarioRepository;
import com.casafacilimoveis.service.AnuncioService;
import com.casafacilimoveis.service.GoogleDriveService;
import com.casafacilimoveis.util.ReportParameter;
import com.casafacilimoveis.util.Util;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.List;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: AnuncioServiceImpl.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Service
public class AnuncioServiceImpl implements AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GoogleDriveService driveService;

    @Override
    public ResponseEntity buscarTodos(Integer id, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Anuncio> anuncios = null;
        if (id != null && id != 0) {
            anuncios = anuncioRepository.buscarTodosPorIdAnunciante(id, pageable);
        } else {
            anuncios = anuncioRepository.findAll(pageable);
        }

        return ResponseEntity.ok((anuncios.getContent()));
    }

    @Override
    public ResponseEntity buscarTodosPorParametros(String rua, String bairro, String cidade, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Anuncio> anuncios = null;
        if (rua != null && !rua.isEmpty() && bairro != null && !bairro.isEmpty() && cidade != null && !cidade.isEmpty()) {
            //busca com todos os parametros
            anuncios = anuncioRepository.findByRuaBairroCidade(rua, bairro, cidade, pageable);
        } else if (rua != null && !rua.isEmpty() && bairro != null && !bairro.isEmpty() && cidade == null) {
            //busca pela rua e bairro
            anuncios = anuncioRepository.findByRuaBairro(rua, bairro, pageable);
        } else if (rua != null && !rua.isEmpty() && bairro == null && cidade != null && !cidade.isEmpty()) {
            //busca pela rua e cidade
            anuncios = anuncioRepository.findByRuaCidade(rua, cidade, pageable);
        } else if (rua == null && bairro != null && !bairro.isEmpty() && cidade != null && !cidade.isEmpty()) {
            //busca pelo bairro e cidade
            anuncios = anuncioRepository.findByBairroCidade(bairro, cidade, pageable);
        } else if (rua != null && !rua.isEmpty() && bairro == null && cidade == null) {
            //busca somente pela rua
            anuncios = anuncioRepository.findByRua(rua, pageable);
        } else if (rua == null && bairro != null && !bairro.isEmpty() && cidade == null) {
            //busca somente pelo bairro
            anuncios = anuncioRepository.findByBairro(bairro, pageable);
        } else if (rua == null && bairro == null && cidade != null && !cidade.isEmpty()) {
            //busca somente pela cidade
            anuncios = anuncioRepository.findByCidade(cidade, pageable);
        }
        return ResponseEntity.ok(anuncios.getContent());
    }

    @Override
    public ResponseEntity buscarPorId(Integer id) {
        Anuncio anuncio = anuncioRepository.findOneById(id);

        if (anuncio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(anuncio);
    }

    @Override
    public ResponseEntity salvar(Anuncio anuncio, Integer id, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }

        Usuario usuario = usuarioRepository.findOneById(id);
        anuncio.setAnunciante(usuario);
        anuncioRepository.save(anuncio);

        return ResponseEntity.ok(anuncio);
    }

    @Override
    public ResponseEntity alterar(Anuncio anuncio, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }

        anuncioRepository.save(anuncio);
        return ResponseEntity.ok(anuncio);
    }

    @Override
    public ResponseEntity excluirPorId(Integer id) {
        Anuncio anuncio = anuncioRepository.getOne(id);

        if (anuncio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            for (Imagem imagem : anuncio.getImagensAnuncios()) {
                if (!driveService.deleteFile(imagem.getId())) {
                    driveService.deleteFile(imagem.getId());
                }
            }
            anuncioRepository.delete(anuncio);
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(CodeError.NAO_PERMITIDO_EXCLUIR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity relatorioVendaAluguel(Integer idUsuario, TipoNegocio tipoNegocio,  HttpServletResponse response) {
        List<Anuncio> anuncios = anuncioRepository.findAllAnunciosByUserAndTipoNegocio(idUsuario, tipoNegocio);
        Usuario usuario = usuarioRepository.findOneById(idUsuario);
        if (anuncios != null && anuncios.size() > 0 && usuario != null) {
            try {
                Resource file = new UrlResource(
                        new File(
                                Util.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario,
                                        new ReportParameter("titulo", "Listagem de Imóveis para Venda/Aluguel")
                                )
                        ).toURI()
                );

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                        .body(file);

            }catch (MalformedURLException e){
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return new ResponseEntity(new ResponseError(CodeError.USUARIO_NAO_POSSUI_ANUNCIOS,
                    "O usuário não possui anúncios suficientes para gerar relatório"), HttpStatus.BAD_REQUEST);

        }
    }

}
