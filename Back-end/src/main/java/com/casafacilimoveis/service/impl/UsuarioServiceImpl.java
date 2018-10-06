package com.casafacilimoveis.service.impl;

import com.casafacilimoveis.model.beans.ResponseError;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Imagem;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.CodeError;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.repository.ImagemRepository;
import com.casafacilimoveis.repository.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private GoogleDriveService googleDriveService;

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
    public ResponseEntity salvar(Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }
        //verifica se o usuário já não existe no banco
        Usuario usuarioExist = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExist != null) {
            return new ResponseEntity(new ResponseError(CodeError.USUARIO_EXISTENTE, "Email já cadastrado"), HttpStatus.BAD_REQUEST);
        } else {
            usuario.setSenha(SenhaUtil.gerarBCrypt(usuario.getSenha()));
            usuarioRepository.save(usuario);
        }

        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity alterar(Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @Override
    public ResponseEntity excluirPorId(Integer id) {
        Usuario usuario = usuarioRepository.getOne(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            for (Anuncio anuncio : usuario.getAnuncios()) {
                for (Imagem imagem : anuncio.getImagensAnuncios()) {
                    googleDriveService.deleteFile(imagem.getId());
                    imagemRepository.delete(imagem);
                }
                anuncioRepository.delete(anuncio);
            }

            usuarioRepository.delete(usuario);
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(CodeError.NAO_PERMITIDO_EXCLUIR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity logarUsuario(String email, String senha) {
        if (email != null && !email.isEmpty() && senha != null && !senha.isEmpty()) {
            Usuario usuario = usuarioRepository.findByEmail(email);
            if (usuario != null) {
                if (SenhaUtil.senhaValida(senha, usuario.getSenha())) {
                    return ResponseEntity.ok(usuario);
                } else {
                    return new ResponseEntity(new ResponseError(CodeError.USUARIO_OU_SENHA_INVALIDOS, "Usuário ou senha inválidos"), HttpStatus.BAD_REQUEST);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
