package com.casafacilimoveis.model.enums;

public enum TipoImovel {
    CASA(0), APARTAMENTO(1), LOJA(2), TERRENO(3), FAZENDA(4), IMOVEL_COMERCIAL(5);

    private int code;

    TipoImovel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
