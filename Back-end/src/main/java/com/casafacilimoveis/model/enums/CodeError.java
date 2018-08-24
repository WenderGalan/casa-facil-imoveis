package com.casafacilimoveis.model.enums;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: CodeError.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
public enum CodeError {
    /**
     * Nao permitido excluir code error.
     */
    NAO_PERMITIDO_EXCLUIR(1000),
    /**
     * Usuario existente code error.
     */
    USUARIO_EXISTENTE(1001),
    /**
     * Usuario ou senha invalidos code error.
     */
    USUARIO_OU_SENHA_INVALIDOS(1002);

    private int code;

    CodeError(int code) {
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
