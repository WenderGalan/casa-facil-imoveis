package com.casafacilimoveis.model.enums;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: TipoNegocio.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
public enum TipoNegocio {

    VENDA(0), ALUGUEL(1);

    private int code;

    TipoNegocio(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
