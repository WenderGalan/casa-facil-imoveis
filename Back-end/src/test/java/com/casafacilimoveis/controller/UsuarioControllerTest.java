package com.casafacilimoveis.controller;

import com.casafacilimoveis.CasaFacilImoveisApplicationTests;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoUsuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: UsuarioControllerTest.java
 * Criado por : Wender Galan
 * Data da criação : 28/08/2018
 * Observação :
 * *********************************************
 */
public class UsuarioControllerTest extends CasaFacilImoveisApplicationTests {

    @Autowired
    private UsuarioController usuarioController;

    private static Usuario usuario;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        usuario = new Usuario();
        usuario.setNome("Wender Galan");
        usuario.setSenha("minhasenha");
        usuario.setEmail("wendergalan2014@hotmail.com");
        usuario.setNumero("67981121278");
        usuario.setTipoUsuario(TipoUsuario.JURIDICA);

        this.mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void contextLoads() {
        assertThat(usuarioController).isNotNull();
    }

    @Test
    public void buscarTodosTest() throws Exception{
        /**EXECUÇÃO**/
        this.mockMvc.perform(get("/usuarios/v1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @After
    public void tearDown(){
        /**LIMPA O BANCO NOVAMENTE**/

    }
}
