package com.casafacilimoveis.repository;

import com.casafacilimoveis.CasaFacilImoveisApplicationTests;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Endereco;
import com.casafacilimoveis.model.entities.Imagem;
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
 * Nome do arquivo: ImagemRepositoryTest.java
 * Criado por : Wender Galan
 * Data da criação : 03/09/2018
 * Observação :
 * *********************************************
 */
public class ImagemRepositoryTest extends CasaFacilImoveisApplicationTests {

    @Autowired
    private ImagemRepository imagemRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Imagem imagem;
    private Anuncio anuncio;
    private Usuario usuario;
    private Endereco endereco;

    @Before
    public void setUp(){
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

        usuario = new Usuario();
        usuario.setNome("Wender Galan");
        usuario.setSenha(SenhaUtil.gerarBCrypt("minhasenha"));
        usuario.setEmail("wendergalan2014@hotmail.com");
        usuario.setNumero("67981121278");
        usuario.setTipoUsuario(TipoUsuario.JURIDICA);


        anuncio = new Anuncio();
        anuncio.setDescricao("Aqui fica a descrição");
        anuncio.setEndereco(endereco);
        anuncio.setImagensAnuncios(null);
        anuncio.setTipoImovel(TipoImovel.IMOVEL_COMERCIAL);
        anuncio.setTitulo("Aqui fica o titulo");
        anuncio.setValor(250000.00);
        anuncio.setAnunciante(usuario);
        anuncioRepository.save(anuncio);

        imagem = new Imagem();
        imagem.setId("125412s6dsd3sd9s51d");
        imagem.setAnuncio(anuncio);
        imagem.setImagemUrl("https://drive.google.com/uc?id=1NzqIM3Li0YlTZDcTkGrIDCQsFUsCZqgm");
        imagemRepository.save(imagem);
    }

    @Test
    public void buscarTodosImagensAnunciosTest(){
        List<Imagem> imagens = imagemRepository.findAll();
        assertThat(imagens.size()).isPositive();
    }

    @After
    public void tearDown(){
        imagemRepository.delete(imagem);
        anuncioRepository.delete(anuncio);
        enderecoRepository.delete(endereco);
        usuarioRepository.delete(usuario);
    }
}
