package com.casafacilimoveis.model.beans;

import com.casafacilimoveis.model.enums.CodeError;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: ResponseError.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
public class ResponseError {

    private CodeError code;
    private String message;

    /**
     * Instantiates a new Response error.
     *
     * @param code the code
     */
    public ResponseError(CodeError code) {
        this.code = code;
    }

    /**
     * Instantiates a new Response error.
     *
     * @param code    the code
     * @param message the message
     */
    public ResponseError(CodeError code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code != null ? code.getCode() : 0;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
