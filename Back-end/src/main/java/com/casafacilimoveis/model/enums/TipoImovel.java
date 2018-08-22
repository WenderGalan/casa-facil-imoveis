package com.casafacilimoveis.model.enums;

/**
 * The enum Tipo imovel.
 */
public enum TipoImovel {
    /**
     * Casa tipo imovel.
     */
    CASA(0),
    /**
     * Apartamento tipo imovel.
     */
    APARTAMENTO(1),
    /**
     * Loja tipo imovel.
     */
    LOJA(2),
    /**
     * Terreno tipo imovel.
     */
    TERRENO(3),
    /**
     * Fazenda tipo imovel.
     */
    FAZENDA(4),
    /**
     * Imovel comercial tipo imovel.
     */
    IMOVEL_COMERCIAL(5);

    private int code;

    TipoImovel(int code) {
        this.code = code;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }
}
