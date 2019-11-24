package com.casafacilimoveis.service.impl;

import com.casafacilimoveis.model.beans.ResponseError;
import com.casafacilimoveis.model.entities.*;
import com.casafacilimoveis.model.enums.CodeError;
import com.casafacilimoveis.repository.*;
import com.casafacilimoveis.service.GoogleDriveService;
import com.casafacilimoveis.service.UsuarioService;
import com.casafacilimoveis.util.SenhaUtil;
import com.casafacilimoveis.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: UsuarioServiceImpl.java
 * Criado por : Wender Galan
 * Data da criação : 25/09/2018
 * Observação :
 * *********************************************
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private GoogleDriveService googleDriveService;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Override
    public ResponseEntity buscarTodos(String search) {
        if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(usuarioRepository.findByNome(search));
        } else {
            return ResponseEntity.ok(usuarioRepository.findAll());
        }
    }

    @Override
    public ResponseEntity buscarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findOneById(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity salvarAnunciante(Anunciante usuario, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }
        //verifica se o usuário já não existe no banco
        Anunciante usuarioExist = anuncianteRepository.findByEmail(usuario.getEmail());

        if (usuarioExist != null)
            return ResponseEntity.badRequest().body(ResponseError.builder().code(CodeError.USUARIO_EXISTENTE).message("Email já cadastrado").build());
        else {
            usuario.setSenha(SenhaUtil.gerarBCrypt(usuario.getSenha()));
            anuncianteRepository.save(usuario);
        }

        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity alterarAnunciante(Anunciante usuario, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }

        anuncianteRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity salvarCliente(Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }
        //verifica se o usuário já não existe no banco
        Cliente usuarioExist = clienteRepository.findByEmail(cliente.getEmail());

        if (usuarioExist != null)
            return ResponseEntity.badRequest().body(ResponseError.builder().code(CodeError.USUARIO_EXISTENTE).message("Email já cadastrado").build());
        else {
            cliente.setSenha(SenhaUtil.gerarBCrypt(cliente.getSenha()));
            clienteRepository.save(cliente);
        }

        return ResponseEntity.ok(cliente);
    }

    @Override
    public ResponseEntity alterarCliente(Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }

        clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }

    @Override
    public ResponseEntity excluirPorId(Integer id) {
        Usuario usuario = usuarioRepository.getOne(id);

        try {
            if (usuario instanceof Anunciante) {
                for (Anuncio anuncio : ((Anunciante) usuario).getAnuncios()) {
                    for (Imagem imagem : anuncio.getImagensAnuncios()) {
                        googleDriveService.deleteFile(imagem.getId());
                        imagemRepository.delete(imagem);
                    }
                    anuncioRepository.delete(anuncio);
                }
            } else if (usuario instanceof Cliente) {
                for (Favorito favorito : ((Cliente) usuario).getAnunciosFavoritos()) {
                    favoritoRepository.delete(favorito);
                }
            }

            usuarioRepository.delete(usuario);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ResponseError.builder().code(CodeError.NAO_PERMITIDO_EXCLUIR).message(ex.getMessage()).build());
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity logarUsuario(String email, String senha) {
        if (email != null && !email.isEmpty() && senha != null && !senha.isEmpty()) {
            Usuario usuario = usuarioRepository.findByEmail(email);
            if (usuario != null) {
                if (SenhaUtil.senhaValida(senha, usuario.getSenha()))
                    return ResponseEntity.ok(usuario);
                else
                    return ResponseEntity.badRequest().body(ResponseError.builder().code(CodeError.USUARIO_OU_SENHA_INVALIDOS).message("Usuário ou senha inválidos").build());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
