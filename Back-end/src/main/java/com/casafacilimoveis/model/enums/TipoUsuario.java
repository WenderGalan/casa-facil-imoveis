package com.casafacilimoveis.model.enums;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: TipoUsuario.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
public enum TipoUsuario {
    /**
     * Fisica tipo usuario.
     */
    FISICA(0),
    /**
     * Corretor tipo usuario.
     */
    CORRETOR(1),
    /**
     * Juridica tipo usuario.
     */
    JURIDICA(2);

    private int code;

    TipoUsuario(int code) {
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
