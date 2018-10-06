package com.casafacilimoveis.service;

import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.enums.TipoNegocio;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: AnuncioService.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
public interface AnuncioService {

    public ResponseEntity buscarTodos(Integer id, Integer page, Integer size);

    public ResponseEntity buscaTodosAutoComplete(String text);

    public ResponseEntity buscarTodosPorParametros(String pesquisa, Integer page, Integer size);

    public ResponseEntity buscarPorId(Integer id);

    public ResponseEntity salvar(Anuncio anuncio, Integer id, BindingResult result);

    public ResponseEntity alterar(Anuncio anuncio, BindingResult result);

    public ResponseEntity excluirPorId(Integer id);

    public ResponseEntity relatorioVendaAluguel(Integer idUsuario, TipoNegocio tipoNegocio);

}
