package com.casafacilimoveis.model.beans;

import com.casafacilimoveis.model.enums.CodeError;
import lombok.Builder;
import lombok.Data;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: ResponseError.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Data
@Builder
public class ResponseError {
    private CodeError code;
    private String message;
}
