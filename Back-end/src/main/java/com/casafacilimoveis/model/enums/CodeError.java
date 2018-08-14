package com.casafacilimoveis.model.enums;

public enum CodeError {
    NAO_PERMITIDO_EXCLUIR(1000), USUARIO_EXISTENTE(1001), USUARIO_OU_SENHA_INVALIDOS(1002);

    private int code;

    CodeError(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
