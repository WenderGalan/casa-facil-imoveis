package com.casafacilimoveis.service.impl;

import com.casafacilimoveis.model.beans.ResponseError;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Imagem;
import com.casafacilimoveis.model.entities.SugestaoAutoComplete;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.CodeError;
import com.casafacilimoveis.model.enums.TipoNegocio;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.repository.EnderecoRepository;
import com.casafacilimoveis.repository.UsuarioRepository;
import com.casafacilimoveis.service.AnuncioService;
import com.casafacilimoveis.service.EmailService;
import com.casafacilimoveis.service.GoogleDriveService;
import com.casafacilimoveis.util.ReportParameter;
import com.casafacilimoveis.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
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
    private EnderecoRepository enderecoRepository;

    @Autowired
    private GoogleDriveService driveService;

    @Autowired
    private EmailService emailService;


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
    public ResponseEntity buscaTodosAutoComplete(String text) {
        if (text != null && !text.isEmpty() && text.length() > 2) {
            List<String> cidades = enderecoRepository.findCidadesAutoComplete(text.toLowerCase());
            List<String> bairros = enderecoRepository.findBairroAutoComplete(text.toLowerCase());
            List<String> enderecos = enderecoRepository.findEnderecoAutoComplete(text.toLowerCase());
            return ResponseEntity.ok(SugestaoAutoComplete.criaSugestaoAutoComplete(cidades, bairros, enderecos));
        } else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @Override
    public ResponseEntity buscarTodosPorParametros(String pesquisa, Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Anuncio> anuncios = anuncioRepository.findAnunciosByParams(pesquisa, pageable);
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
    public ResponseEntity relatorioVendaAluguel(Integer idUsuario, TipoNegocio tipoNegocio) {
        List<Anuncio> anuncios = anuncioRepository.findAllAnunciosByUserAndTipoNegocio(idUsuario, tipoNegocio);
        Usuario usuario = usuarioRepository.findOneById(idUsuario);
        if (anuncios != null && anuncios.size() > 0 && usuario != null) {
            String subject = null;
            String titulo = null;
            if (tipoNegocio == TipoNegocio.VENDA) {
                subject = "Relatório de vendas - Casa Fácil Imóveis";
                titulo = "Relatório de imóveis à venda";
            } else {
                subject = "Relatório de aluguéis - Casa Fácil Imóveis";
                titulo = "Relatório de imóveis para alugar";
            }

            String text = "Olá " + usuario.getNome() + " segue anexo o relatório solicitado!\n" +
                    "Caso tenha alguma sugestão para incluir nos relatórios por favor responda a este e-mail!\n\n\n\n" +
                    "Atenciosamente,\nEquipe Casa Fácil Imóveis.";


            String pdfGerado = Util.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario,
                    new ReportParameter("titulo", titulo)
            );
            return emailService.sendEmailWithAttachement(pdfGerado, usuario.getEmail(), subject, text);
        } else {
            return new ResponseEntity(new ResponseError(CodeError.USUARIO_NAO_POSSUI_ANUNCIOS,
                    "O usuário não possui anúncios suficientes para gerar relatório"), HttpStatus.BAD_REQUEST);
        }
    }

}
