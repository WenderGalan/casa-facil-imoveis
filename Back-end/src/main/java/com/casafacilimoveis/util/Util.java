package com.casafacilimoveis.util;

import com.casafacilimoveis.model.beans.Validation;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.TipoRelatorio;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.*;
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

}
