package com.casafacilimoveis.util;

import com.casafacilimoveis.model.beans.Validation;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoRelatorio;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.validation.ObjectError;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static com.casafacilimoveis.model.enums.TipoRelatorio.*;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Util.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
public class Util {

    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String REPORT_DIR = System.getProperty("user.dir") + FILE_SEPARATOR + "reportdir" + FILE_SEPARATOR;
    private static final String JASPER_DIR = System.getProperty("user.dir") + FILE_SEPARATOR + "jasperdir" + FILE_SEPARATOR;


    /**
     * Criar lista de erros da validacao list.
     *
     * @param erros the erros
     * @return the list
     */
    public static List<Validation> criarListaDeErrosDaValidacao(List<ObjectError> erros) {
        List<Validation> lista = new ArrayList<>();
        if (erros != null && !erros.isEmpty()) {
            for (ObjectError error : erros) {
                lista.add(new Validation(error.getObjectName(), error.getDefaultMessage()));
            }
        }
        return lista;
    }

    /**
     * Convert file.
     *
     * @param file the file
     * @return the file
     * @throws IllegalStateException the illegal state exception
     * @throws IOException           the io exception
     */
    public static File convert(MultipartFile file) throws IllegalStateException, IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

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

            if (tipoRelatorio != null && tipoRelatorio == PDF || tipoRelatorio == HTML || tipoRelatorio == XML) {
                //o nome do arquivo pdf será a data/hora atual no formato long
                String nomeArquivo = null;
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
                        nomeArquivo = new Date().getTime() + ".xml";
                        JasperExportManager.exportReportToXmlFile(jasperPrint, REPORT_DIR + nomeArquivo, false);
                        break;
                }
                //retorna o caminho do pdf gerado
                return REPORT_DIR + nomeArquivo;
            } else if (tipoRelatorio != null && tipoRelatorio == CSV) {
                String nomeArquivo = new Date().getTime() + ".csv";
                convertToCsv(jasperPrint, nomeArquivo);
                return REPORT_DIR + nomeArquivo;
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void convertToCsv(JasperPrint jasperPrint, String nomeArquivo){
        try {
            JRCsvExporter exporterCSV = new JRCsvExporter();
            exporterCSV.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporterCSV.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, REPORT_DIR + nomeArquivo);
            exporterCSV.exportReport();
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

}
