package com.casafacilimoveis.repository;

import com.casafacilimoveis.CasaFacilImoveisApplicationTests;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Endereco;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoImovel;
import com.casafacilimoveis.model.enums.TipoUsuario;
import com.casafacilimoveis.util.SenhaUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: AnuncioRepositoryTest.java
 * Criado por : Wender Galan
 * Data da criação : 29/08/2018
 * Observação :
 * *********************************************
 */
public class AnuncioRepositoryTest extends CasaFacilImoveisApplicationTests {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private static Endereco endereco;
    private static Anuncio anuncio;
    private static Usuario usuario;

    @Before
    public void setUp() {
        endereco = new Endereco();
        endereco.setBairro("Universitário");
        endereco.setCep("79071-203");
        endereco.setCidade("Campo Grande");
        endereco.setEstado("MS");
        endereco.setLatitude(0.0);
        endereco.setLongitude(0.0);
        endereco.setNumero(51);
        endereco.setEndereco("Rua elesbão murtinho");
        endereco.setComplemento("Na minha casa");
        /*enderecoRepository.save(endereco);*/

        usuario = new Usuario();
        usuario.setNome("Wender Galan");
        usuario.setSenha(SenhaUtil.gerarBCrypt("minhasenha"));
        usuario.setEmail("wendergalan2014@hotmail.com");
        usuario.setNumero("67981121278");
        usuario.setTipoUsuario(TipoUsuario.JURIDICA);
        //usuarioRepository.save(usuario);

        anuncio = new Anuncio();
        anuncio.setDescricao("Aqui fica a descrição");
        anuncio.setEndereco(endereco);
        anuncio.setImagensAnuncios(null);
        anuncio.setTipoImovel(TipoImovel.IMOVEL_COMERCIAL);
        anuncio.setTitulo("Aqui fica o titulo");
        anuncio.setValor(250000.00);
        anuncio.setAnunciante(usuario);
        anuncioRepository.save(anuncio);
    }

    @Test
    public void buscarTodosAnunciosTest(){
        List<Anuncio> anuncios = anuncioRepository.findAll();
        assertThat(anuncios.size()).isPositive();
    }

    @Test
    public void buscarTodosPorIdAnuncianteTest(){
        List<Anuncio> anuncios = anuncioRepository.buscarTodosPorIdAnunciante(usuario.getId());
        assertThat(anuncios.size()).isPositive();
    }

    @After
    public void tearDown(){
        anuncioRepository.delete(anuncio);
        enderecoRepository.delete(endereco);
        usuarioRepository.delete(usuario);
    }
}
