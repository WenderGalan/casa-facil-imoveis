package com.casafacilimoveis.util;

import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoRelatorio;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.casafacilimoveis.model.enums.TipoRelatorio.*;

/**
 * casafacilimoveis
 * TDR Informática Ltda
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: RelatorioUtil.java
 * Criado por : Wender Galan
 * Data da criação : 15/10/2018
 * Observação :
 * *********************************************
 */
public class RelatorioUtil {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String REPORT_DIR = System.getProperty("user.dir") + FILE_SEPARATOR + "reportdir" + FILE_SEPARATOR;
    private static final String JASPER_DIR = System.getProperty("user.dir") + FILE_SEPARATOR + "jasperdir" + FILE_SEPARATOR;

    public static String gerarRelatorio(String layout, List result, Usuario usuario, TipoRelatorio tipoRelatorio, ReportParameter... reportParameters) {
        try {
            //criando o layout do relatorio a partir do jasper
            JasperDesign desenho = JRXmlLoader.load(JASPER_DIR + layout);
            JasperReport relatorio = JasperCompileManager.compileReport(desenho);
            JRDataSource jrRS = new JRBeanCollectionDataSource(result);

            //setando os parametros
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("dataAtual", new Date());
            parameters.put("usuario", usuario != null ? usuario.getNome() : "Usuário Anônimo");
            if (reportParameters != null) {
                for (ReportParameter parameter : reportParameters) {
                    parameters.put(parameter.getKey(), parameter.getValue());
                }
            }

            //gerando o pdf
            JasperPrint jasperPrint = JasperFillManager.fillReport(relatorio, parameters, jrRS);
            File outDir = new File(REPORT_DIR);
            outDir.mkdirs();

            /**Os arquivos são gerados com o nome em forma de data para nao ocorrer de sobreescrever arquivos no servidor**/
            if (tipoRelatorio != null && tipoRelatorio == PDF || tipoRelatorio == HTML || tipoRelatorio == XML) {
                String nomeArquivo = null;
                //Relatórios nativos sem a necessidade de conversao
                switch (tipoRelatorio) {
                    case PDF:
                        nomeArquivo = new Date().getTime() + ".pdf";
                        JasperExportManager.exportReportToPdfFile(jasperPrint, REPORT_DIR + nomeArquivo);
                        break;
                    case HTML:
                        nomeArquivo = new Date().getTime() + ".html";
                        JasperExportManager.exportReportToHtmlFile(jasperPrint, REPORT_DIR + nomeArquivo);
                        break;
                    case XML:
                        //Cria um arquivo com a imagem em byte array junto do arquivo xml
                        //Site para formatar o XML: https://www.freeformatter.com/xml-formatter.html
                        nomeArquivo = new Date().getTime() + ".xml";
                        JasperExportManager.exportReportToXmlFile(jasperPrint, REPORT_DIR + nomeArquivo, true);
                        break;
                }
                return REPORT_DIR + nomeArquivo;
                //Relatorios que precisam de conversao
            } else if (tipoRelatorio != null && tipoRelatorio == CSV) {
                String nomeArquivo = new Date().getTime() + ".csv";
                convertToCsv(jasperPrint, nomeArquivo);
                return REPORT_DIR + nomeArquivo;
            } else if (tipoRelatorio != null && tipoRelatorio == XLS) {
                String nomeArquivo = new Date().getTime() + ".xls";
                convertToXls(jasperPrint, nomeArquivo);
                return REPORT_DIR + nomeArquivo;
            } else if (tipoRelatorio != null && tipoRelatorio == JSON) {
                String nomeArquivo = new Date().getTime() + ".json";
                convertToJson(result, nomeArquivo);
                return REPORT_DIR + nomeArquivo;
            } else if (tipoRelatorio != null && tipoRelatorio == TXT) {
                String nomeArquivo = new Date().getTime() + ".txt";
                convertToTxt(jasperPrint, nomeArquivo);
                return REPORT_DIR + nomeArquivo;
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converte um jasper print em um arquivo CSV
     *
     * @param jasperPrint - desenho do relatorio
     * @param nomeArquivo - nome do arquivo gerado
     **/
    public static void convertToCsv(JasperPrint jasperPrint, String nomeArquivo) {
        try {
            JRCsvExporter exporterCSV = new JRCsvExporter();
            exporterCSV.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporterCSV.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, REPORT_DIR + nomeArquivo);
            exporterCSV.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    /**
     * Converte um jasper print em um arquivo XLS
     *
     * @param jasperPrint - desenho do relatorio
     * @param nomeArquivo - nome do arquivo gerado
     **/
    public static void convertToXls(JasperPrint jasperPrint, String nomeArquivo) {
        try {
            JRXlsExporter exporterXLS = new JRXlsExporter();
            exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, REPORT_DIR + nomeArquivo);
            exporterXLS.exportReport();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Converte uma lista de anuncios em um arquivo JSON
     * Este site formata o JSON para visualização caso precise:
     * https://jsonformatter.curiousconcept.com/
     *
     * @param result      - lista de anuncios
     * @param nomeArquivo - nome do arquivo Json
     **/
    public static void convertToJson(List<Anuncio> result, String nomeArquivo) {
        try {
            //seta a senha do anunciante para nulo para nao enviar junto
            for (Anuncio anuncio : result) {
                if (anuncio != null && anuncio.getAnunciante() != null) {
                    anuncio.getAnunciante().setSenha(null);
                }
            }
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(REPORT_DIR + nomeArquivo), result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Convert um jasper print para um arquivo txt
     *
     * @param jasperPrint - Desenho do relatório
     * @param nomeArquivo - Nome do arquivo gerado
     **/
    public static void convertToTxt(JasperPrint jasperPrint, String nomeArquivo) {
        try {
            JRTextExporter exporter = new JRTextExporter();
            exporter.setParameter(JRTextExporterParameter.PAGE_WIDTH, 150);
            exporter.setParameter(JRTextExporterParameter.PAGE_HEIGHT, 60);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, REPORT_DIR + nomeArquivo);
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
