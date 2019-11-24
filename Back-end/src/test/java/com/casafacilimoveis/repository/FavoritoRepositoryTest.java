package com.casafacilimoveis.repository;

import com.casafacilimoveis.CasaFacilImoveisApplicationTests;
import com.casafacilimoveis.model.entities.*;
import com.casafacilimoveis.model.enums.TipoImovel;
import com.casafacilimoveis.model.enums.TipoNegocio;
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
 * Nome do arquivo: FavoritoRepositoryTest.java
 * Criado por : Wender Galan
 * Data da criação : 11/11/2018
 * Observação :
 * *********************************************
 */
public class FavoritoRepositoryTest extends CasaFacilImoveisApplicationTests {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AnuncianteRepository anuncianteRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    private static Anunciante anunciante;
    private static Anuncio anuncio;
    private static Cliente cliente;
    private static Endereco endereco;
    private static Favorito favorito;

    @Before
    public void setUp() {
        cliente = new Cliente();
        cliente.setCpf("05894600138");
        cliente.setNome("Wender Galan");
        cliente.setSenha(SenhaUtil.gerarBCrypt("minhasenha"));
        cliente.setEmail("wendergalan2014444444444@hotmail.com");
        cliente.setNumero("67981121278");
        cliente.setTipoUsuario(TipoUsuario.FISICA);
        clienteRepository.save(cliente);

        anunciante = new Anunciante();
        anunciante.setNome("Wender Galan");
        anunciante.setSenha(SenhaUtil.gerarBCrypt("minhasenha"));
        anunciante.setEmail("wendergalan2014444444444@hotmail.com");
        anunciante.setNumero("67981121278");
        anunciante.setTipoUsuario(TipoUsuario.JURIDICA);
        anunciante.setCnpj("09103131000179");
        //anuncianteRepository.save(anunciante);

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

        anuncio = new Anuncio();
        anuncio.setDescricao("Aqui fica a descrição");
        anuncio.setEndereco(endereco);
        anuncio.setImagensAnuncios(null);
        anuncio.setTipoNegocio(TipoNegocio.VENDA);
        anuncio.setTipoImovel(TipoImovel.IMOVEL_COMERCIAL);
        anuncio.setTitulo("Aqui fica o titulo");
        anuncio.setValor(250000.00);
        anuncio.setAnunciante(anunciante);
        anuncioRepository.save(anuncio);

        favorito = Favorito.builder().anuncio(anuncio).cliente(cliente).build();
        favoritoRepository.save(favorito);
    }

    @Test
    public void findAllByIdCliente() {
        List<Favorito> favoritos = favoritoRepository.findAllByIdCliente(cliente.getId());
        assertThat(favoritos.size()).isPositive();
    }

    @Test
    public void findByClienteAndAnuncio() {
        Favorito favorito = favoritoRepository.findByClienteAndAnuncio(cliente.getId(), anuncio.getId());
        assertThat(favorito).isNotNull();
    }

    @After
    public void tearDown() {
        favoritoRepository.delete(favorito);
        clienteRepository.delete(cliente);
        anuncioRepository.delete(anuncio);
        anuncianteRepository.delete(anunciante);
    }
}
