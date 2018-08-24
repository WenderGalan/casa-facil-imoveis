package com.casafacilimoveis.controller;

import com.casafacilimoveis.model.beans.ResponseError;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Imagem;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.CodeError;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.repository.ImagemRepository;
import com.casafacilimoveis.repository.UsuarioRepository;
import com.casafacilimoveis.service.GoogleDriveService;
import com.casafacilimoveis.util.SenhaUtil;
import com.casafacilimoveis.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: UsuarioController.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
@Api(description = "Controller de requisições de usuário")
@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private GoogleDriveService googleDriveService;

    /**
     * Buscar todos response entity.
     *
     * @param search the search
     * @return the response entity
     */
    @ApiOperation("Busca todos os usuários do sistema")
    @GetMapping("/v1")
    public ResponseEntity buscarTodos(@RequestParam(value = "search", required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(usuarioRepository.findByNome(search));
        } else {
            return ResponseEntity.ok(usuarioRepository.findAll());
        }
    }

    /**
     * Buscar por id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation("Busca apenas um usuário pelo o ID")
    @GetMapping("/v1/{id}")
    public ResponseEntity buscarPorId(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioRepository.findOneById(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(usuario);
    }

    /**
     * Salvar response entity.
     *
     * @param usuario the usuario
     * @param result  the result
     * @return the response entity
     */
    @ApiOperation("Salva o usuário")
    @PostMapping("/v1")
    public ResponseEntity salvar(@Valid @RequestBody Usuario usuario, BindingResult result) {
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

    /**
     * Alterar response entity.
     *
     * @param usuario the usuario
     * @param result  the result
     * @return the response entity
     */
    @ApiOperation("Altera o usuário")
    @PutMapping("/v1")
    public ResponseEntity alterar(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }

        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    /**
     * Excluir por id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation("Exclui o usuário pelo ID (deleta todos os anúncios e imagens vinculados a ele)")
    @DeleteMapping("/v1/{id}")
    public ResponseEntity excluirPorId(@PathVariable("id") Integer id) {
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

    /**
     * Logar usuario response entity.
     *
     * @param email the email
     * @param senha the senha
     * @return the response entity
     */
    @ApiOperation("Login do usuário")
    @GetMapping("/v1/login")
    public ResponseEntity logarUsuario(@RequestParam(value = "email") String email, @RequestParam(value = "senha") String senha) {
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
