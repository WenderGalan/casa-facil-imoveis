package com.casafacilimoveis.controller;

import com.casafacilimoveis.service.FavoritoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: FavoritoController.java
 * Criado por : Wender Galan
 * Data da criação : 11/11/2018
 * Observação :
 * *********************************************
 */
@Api(description = "Controller de requisições de favoritos")
@RestController
@CrossOrigin
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritoService favoritoService;

    /**
     * Salvar response entity.
     *
     * @param cliente - the cliente id
     * @param anuncio the anuncio id
     * @return the response entity
     */
    @ApiOperation("Salva o favorito")
    @PostMapping("/v1/cliente/{idCliente}/anuncio/{idAnuncio}")
    public ResponseEntity salvar(@PathVariable("idCliente") Integer cliente, @PathVariable("idAnuncio") Integer anuncio) {
        return favoritoService.salvar(cliente, anuncio);
    }

    /**
     * Deleta response entity.
     *
     * @param id the id by favorito
     * @return the response entity
     */
    @ApiOperation("Deleta o favorito pelo ID")
    @DeleteMapping("/v1/{id}")
    public ResponseEntity excluirPorId(@PathVariable("id") Integer id) {
        return favoritoService.excluirPorId(id);
    }

    /**
     * Busca todos os favoritos com base no cliente
     *
     * @param cliente - ID do cliente
     * @return a list of favoritos
     **/
    @ApiOperation("Busca todos os favoritos com base no cliente")
    @GetMapping("/v1/cliente/{id}")
    public ResponseEntity buscarTodosPorCliente(@PathVariable("id") Integer cliente) {
        return favoritoService.buscarTodosPorCliente(cliente);
    }
}
