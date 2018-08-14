package com.casafacilimoveis.model.beans;

import com.casafacilimoveis.model.enums.CodeError;

public class ResponseError {

    private CodeError code;
    private String message;

    public ResponseError(CodeError code) {
        this.code = code;
    }

    public ResponseError(CodeError code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code != null ? code.getCode() : 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
