package com.casafacilimoveis.controller;

import com.casafacilimoveis.model.entities.Anunciante;
import com.casafacilimoveis.model.entities.Cliente;
import com.casafacilimoveis.service.UsuarioService;
import com.casafacilimoveis.util.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = {Constantes.CACHE_USUARIOS})
@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Buscar todos response entity.
     *
     * @param search the search
     * @return the response entity
     */
    @ApiOperation("Busca todos os usuários do sistema")
    @Cacheable
    @GetMapping("/v1")
    public ResponseEntity buscarTodos(@RequestParam(value = "search", required = false) String search) {
        return usuarioService.buscarTodos(search);
    }

    /**
     * Buscar por id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation("Busca apenas um usuário pelo o ID")
    @Cacheable
    @GetMapping("/v1/{id}")
    public ResponseEntity buscarPorId(@PathVariable("id") Integer id) {
        return usuarioService.buscarPorId(id);
    }

    /**
     * Salvar response entity.
     *
     * @param usuario the usuario
     * @param result  the result
     * @return the response entity
     */
    @ApiOperation("Salva o usuário do tipo anunciante")
    @CacheEvict(value = Constantes.CACHE_USUARIOS, allEntries = true)
    @PostMapping("/v1/anunciante")
    public ResponseEntity salvarAnunciante(@Valid @RequestBody Anunciante usuario, BindingResult result) {
        return usuarioService.salvarAnunciante(usuario, result);
    }

    /**
     * Salvar response entity.
     *
     * @param cliente the usuario
     * @param result  the result
     * @return the response entity
     */
    @ApiOperation("Salva o usuário do tipo cliente")
    @CacheEvict(value = Constantes.CACHE_USUARIOS, allEntries = true)
    @PostMapping("/v1/cliente")
    public ResponseEntity salvarCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
        return usuarioService.salvarCliente(cliente, result);
    }

    /**
     * Alterar response entity.
     *
     * @param anunciante the usuario anunciante
     * @param result  the result
     * @return the response entity
     */
    @ApiOperation("Altera o usuário do tipo cliente")
    @CacheEvict(value = Constantes.CACHE_USUARIOS, allEntries = true)
    @PutMapping("/v1/anunciante")
    public ResponseEntity alterarAnunciante(@Valid @RequestBody Anunciante anunciante, BindingResult result) {
        return usuarioService.alterarAnunciante(anunciante, result);
    }

    /**
     * Alterar response entity.
     *
     * @param cliente the usuario
     * @param result  the result
     * @return the response entity
     */
    @ApiOperation("Altera o usuário do tipo cliente")
    @CacheEvict(value = Constantes.CACHE_USUARIOS, allEntries = true)
    @PutMapping("/v1/cliente")
    public ResponseEntity alterarCliente(@Valid @RequestBody Cliente cliente, BindingResult result) {
        return usuarioService.alterarCliente(cliente, result);
    }

    /**
     * Excluir por id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation("Exclui o usuário pelo ID (deleta todos os anúncios e imagens vinculados a ele)")
    @CacheEvict(value = Constantes.CACHE_USUARIOS, allEntries = true)
    @DeleteMapping("/v1/{id}")
    public ResponseEntity excluirPorId(@PathVariable("id") Integer id) {
        return usuarioService.excluirPorId(id);
    }

    /**
     * Logar usuario response entity.
     *
     * @param email the email
     * @param senha the senha
     * @return the response entity
     */
    @ApiOperation("Login do usuário")
    @Cacheable
    @GetMapping("/v1/login")
    public ResponseEntity logarUsuario(@RequestParam(value = "email") String email, @RequestParam(value = "senha") String senha) {
        return usuarioService.logarUsuario(email, senha);
    }
}
