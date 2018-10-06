package com.casafacilimoveis.util;

import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Endereco;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoImovel;
import com.casafacilimoveis.model.enums.TipoNegocio;
import com.casafacilimoveis.model.enums.TipoUsuario;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UtilTest {

    private Usuario usuario;
    private List<Anuncio> anuncios = new ArrayList<>();
    private String pdfGerado = null;
    private File file;

    @Before
    public void setUp() {
        Endereco endereco = new Endereco();
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

        Anuncio anuncio1 = new Anuncio();
        anuncio1.setDescricao("Aqui fica a descrição");
        anuncio1.setEndereco(endereco);
        anuncio1.setImagensAnuncios(null);
        anuncio1.setTipoNegocio(TipoNegocio.VENDA);
        anuncio1.setTipoImovel(TipoImovel.IMOVEL_COMERCIAL);
        anuncio1.setTitulo("Aqui fica o titulo");
        anuncio1.setValor(250000.00);
        anuncio1.setAnunciante(usuario);

        Anuncio anuncio2 = new Anuncio();
        anuncio2.setDescricao("Aqui fica a descrição");
        anuncio2.setEndereco(endereco);
        anuncio2.setImagensAnuncios(null);
        anuncio2.setTipoNegocio(TipoNegocio.VENDA);
        anuncio2.setTipoImovel(TipoImovel.IMOVEL_COMERCIAL);
        anuncio2.setTitulo("Aqui fica o titulo");
        anuncio2.setValor(250000.00);
        anuncio2.setAnunciante(usuario);

        anuncios.add(anuncio1);
        anuncios.add(anuncio2);
    }

    @Test
    public void gerarRelatorioTest() {
        pdfGerado = Util.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario,
                new ReportParameter("titulo", "TITULO TEST")
        );
        file = new File(pdfGerado);
        Assert.assertTrue(file.exists());
    }

    @After
    public void tearDown() {
        file.delete();
    }
}
