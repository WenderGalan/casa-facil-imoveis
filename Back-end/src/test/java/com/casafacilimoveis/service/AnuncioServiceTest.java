package com.casafacilimoveis.service;

import com.casafacilimoveis.CasaFacilImoveisApplicationTests;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Endereco;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoImovel;
import com.casafacilimoveis.model.enums.TipoUsuario;
import com.casafacilimoveis.util.SenhaUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: AnuncioServiceTest.java
 * Criado por : Wender Galan
 * Data da criação : 27/09/2018
 * Observação :
 * *********************************************
 */
public class AnuncioServiceTest extends CasaFacilImoveisApplicationTests {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    public void buscarTodosAutoCompleteTest(){
        assertThat(anuncioService.buscaTodosAutoComplete("univ").getBody()).isNotNull();
    }

    @Test
    public void buscarTodosPorParametrosTest(){
        assertThat(anuncioService.buscarTodosPorParametros("campo", 0, 10).getBody()).isNotNull();
    }
}
