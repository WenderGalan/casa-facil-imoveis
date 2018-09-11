package com.casafacilimoveis.controller;

import com.casafacilimoveis.CasaFacilImoveisApplicationTests;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Endereco;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoImovel;
import com.casafacilimoveis.model.enums.TipoUsuario;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.repository.EnderecoRepository;
import com.casafacilimoveis.repository.UsuarioRepository;
import com.casafacilimoveis.util.SenhaUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: AnuncioControllerTest.java
 * Criado por : Wender Galan
 * Data da criação : 10/09/2018
 * Observação :
 * *********************************************
 */
public class AnuncioControllerTest extends CasaFacilImoveisApplicationTests {

    @Autowired
    private AnuncioController anuncioController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(anuncioController).build();
    }

    @Test
    public void contextLoads() {
        assertThat(anuncioController).isNotNull();
    }

    @Test
    public void buscarTodosTest() throws Exception {
        this.mockMvc.perform(get("/anuncios/v1").param("id", "184954165454"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void buscarAnuncioPorIdTest() throws Exception {
        this.mockMvc.perform(get("/anuncios/v1/" + 565662639))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @After
    public void tearDown(){}
}
