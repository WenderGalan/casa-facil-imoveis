package com.casafacilimoveis.util;

import com.casafacilimoveis.model.beans.Validation;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static List<Validation> criarListaDeErrosDaValidacao(List<ObjectError> erros){
        List<Validation> lista = new ArrayList<>();
        if(erros != null && !erros.isEmpty()){
            for(ObjectError error : erros){
                lista.add(new Validation(error.getObjectName(), error.getDefaultMessage()));
            }
        }
        return lista;
    }
}
