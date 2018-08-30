package com.casafacilimoveis.controller;

import com.casafacilimoveis.CasaFacilImoveisApplicationTests;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoUsuario;
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
 * Nome do arquivo: UsuarioControllerTest.java
 * Criado por : Wender Galan
 * Data da criação : 28/08/2018
 * Observação :
 * *********************************************
 */
public class UsuarioControllerTest extends CasaFacilImoveisApplicationTests {

    @Autowired
    private UsuarioController usuarioController;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static Usuario usuario;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        usuario = new Usuario();
        usuario.setNome("Wender Galan");
        usuario.setSenha(SenhaUtil.gerarBCrypt("minhasenha"));
        usuario.setEmail("wendergalan2014@hotmail.com");
        usuario.setNumero("67981121278");
        usuario.setTipoUsuario(TipoUsuario.JURIDICA);
        usuarioRepository.save(usuario);

        this.mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
    }

    @Test
    public void contextLoads() {
        assertThat(usuarioController).isNotNull();
    }

    @Test
    public void buscarTodosTest() throws Exception {
        this.mockMvc.perform(get("/usuarios/v1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void buscarUsuarioPorId() throws Exception {
        this.mockMvc.perform(get("/usuarios/v1/" + usuario.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /*@Test
    public void salvarTest() throws Exception {
        this.mockMvc.perform(get("/usuarios/v1/login")
                .param("email", "matheus200713@gmail.com")
                .param("senha", SenhaUtil.gerarBCrypt("123456789")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }*/

    @After
    public void tearDown() {
        /**LIMPA O BANCO NOVAMENTE**/
        usuarioRepository.delete(usuario);
    }
}
