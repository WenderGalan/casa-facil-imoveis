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

import static com.casafacilimoveis.model.enums.TipoRelatorio.*;

/**
 * casafacilimoveis
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: RelatorioUtilTest.java
 * Criado por : Wender Galan
 * Data da criação : 15/10/2018
 * Observação :
 * *********************************************
 */
public class RelatorioUtilTest {

    private Usuario usuario;
    private List<Anuncio> anuncios = new ArrayList<>();
    private String PDFGerado = null;
    private String HTMLGerado = null;
    private String XMLGerado = null;
    private String TXTGerado = null;
    private String JSONGerado = null;
    private String CSVGerado = null;
    private String XLSGerado = null;
    private File filePDF;
    private File fileHTML;
    private File fileXML;
    private File fileTXT;
    private File fileJSON;
    private File fileCSV;
    private File fileXLS;

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

   /* @Test
    public void gerarRelatorioPDFTest() {
        PDFGerado = RelatorioUtil.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario, PDF,
                new ReportParameter("titulo", "TITULO TEST")
        );
        filePDF = new File(PDFGerado);
        Assert.assertTrue(filePDF.exists());
    }

    @Test
    public void gerarRelatorioHTMLTest() {
        HTMLGerado = RelatorioUtil.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario, HTML,
                new ReportParameter("titulo", "TITULO TEST")
        );
        fileHTML = new File(HTMLGerado);
        Assert.assertTrue(fileHTML.exists());
    }

    @Test
    public void gerarRelatorioXMLTest() {
        XMLGerado = RelatorioUtil.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario, XML,
                new ReportParameter("titulo", "TITULO TEST")
        );
        fileXML = new File(XMLGerado);
        Assert.assertTrue(fileXML.exists());
    }

    @Test
    public void gerarRelatorioTXTTest() {
        TXTGerado = RelatorioUtil.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario, TXT,
                new ReportParameter("titulo", "TITULO TEST")
        );
        fileTXT = new File(TXTGerado);
        Assert.assertTrue(fileTXT.exists());
    }

    @Test
    public void gerarRelatorioJSONTest() {
        JSONGerado = RelatorioUtil.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario, JSON,
                new ReportParameter("titulo", "TITULO TEST")
        );
        fileJSON = new File(JSONGerado);
        Assert.assertTrue(fileJSON.exists());
    }

    @Test
    public void gerarRelatorioCSVTest() {
        CSVGerado = RelatorioUtil.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario, CSV,
                new ReportParameter("titulo", "TITULO TEST")
        );
        fileCSV = new File(CSVGerado);
        Assert.assertTrue(fileCSV.exists());
    }

    @Test
    public void gerarRelatorioXLSTest() {
        XLSGerado = RelatorioUtil.gerarRelatorio("listagemImoveis.jrxml", anuncios, usuario, XLS,
                new ReportParameter("titulo", "TITULO TEST")
        );
        fileXLS = new File(XLSGerado);
        Assert.assertTrue(fileXLS.exists());
    }*/

    @After
    public void tearDown() {
        if (filePDF != null) filePDF.delete();
        if (fileHTML != null) fileHTML.delete();
        if (fileXML != null) fileXML.delete();
        if (fileTXT != null) fileTXT.delete();
        if (fileCSV != null) fileCSV.delete();
        if (fileXLS != null) fileXLS.delete();
        if (fileJSON != null) fileJSON.delete();
    }
}
