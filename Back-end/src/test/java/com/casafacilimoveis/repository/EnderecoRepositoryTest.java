package com.casafacilimoveis.repository;

import com.casafacilimoveis.CasaFacilImoveisApplicationTests;
import com.casafacilimoveis.model.entities.Endereco;
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
 * Nome do arquivo: EnderecoRepositoryTest.java
 * Criado por : Wender Galan
 * Data da criação : 29/08/2018
 * Observação :
 * *********************************************
 */
public class EnderecoRepositoryTest extends CasaFacilImoveisApplicationTests {

    @Autowired
    private EnderecoRepository enderecoRepository;

    private static Endereco endereco;

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
        enderecoRepository.save(endereco);
    }

    @Test
    public void alterarEnderecoTest() {
        endereco.setLatitude(87452152.38);
        endereco.setLongitude(87452152.38);
        enderecoRepository.save(endereco);
        assertThat(endereco.getLatitude()).isNotNull();
    }

    @Test
    public void buscarTodosEnderecosTest() {
        List<Endereco> enderecos = enderecoRepository.findAll();
        assertThat(enderecos).isNotNull();
    }

    @After
    public void tearDown() {
        enderecoRepository.delete(endereco);
    }

}
