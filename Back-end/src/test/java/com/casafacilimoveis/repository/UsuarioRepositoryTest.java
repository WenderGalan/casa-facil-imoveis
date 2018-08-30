package com.casafacilimoveis.repository;

import com.casafacilimoveis.CasaFacilImoveisApplicationTests;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoUsuario;
import com.casafacilimoveis.util.SenhaUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: UsuarioRepositoryTest.java
 * Criado por : Wender Galan
 * Data da criação : 29/08/2018
 * Observação :
 * *********************************************
 */
public class UsuarioRepositoryTest extends CasaFacilImoveisApplicationTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private static Usuario usuario;

    @Before
    public void setUp() {
        usuario = new Usuario();
        usuario.setNome("Wender Galan");
        usuario.setSenha(SenhaUtil.gerarBCrypt("minhasenha"));
        usuario.setEmail("wendergalan2014@hotmail.com");
        usuario.setNumero("67981121278");
        usuario.setTipoUsuario(TipoUsuario.JURIDICA);
        usuarioRepository.save(usuario);
    }

    @Test
    public void updateUsuarioTest() {
        usuario.setTipoUsuario(TipoUsuario.FISICA);
        usuarioRepository.save(usuario);
        assertThat(usuario.getTipoUsuario()).isEqualTo(TipoUsuario.FISICA);
    }

    @Test
    public void buscarUsuarioTest() {
        List<Usuario> usuarioList = usuarioRepository.findAll();
        assertThat(usuarioList).isNotNull();
    }

    @Test
    public void buscarUsuarioPorIdTest() {
        Usuario retornoUsuario = usuarioRepository.findOneById(usuario.getId());
        assertThat(retornoUsuario).isNotNull();
    }

    @Test
    public void buscarUsuarioPorEmailTest() {
        Usuario retornoUsuario = usuarioRepository.findByEmail(usuario.getEmail());
        assertThat(retornoUsuario).isNotNull();
    }

    @After
    public void tearDown() {
        usuarioRepository.delete(usuario);
    }

}
