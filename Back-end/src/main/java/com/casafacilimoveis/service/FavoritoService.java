package com.casafacilimoveis.service;

import org.springframework.http.ResponseEntity;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: FavoritoService.java
 * Criado por : Wender Galan
 * Data da criação : 11/11/2018
 * Observação :
 * *********************************************
 */
public interface FavoritoService {

    public ResponseEntity salvar(Integer cliente, Integer anuncio);

    public ResponseEntity excluirPorId(Integer id);

    public ResponseEntity buscarTodosPorCliente(Integer cliente);
}
