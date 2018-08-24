package com.casafacilimoveis.model.beans;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: Validation.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
public class Validation {

    private String campo;
    private String mensagem;

    /**
     * Instantiates a new Validation.
     */
    public Validation() {
    }

    /**
     * Instantiates a new Validation.
     *
     * @param campo    the campo
     * @param mensagem the mensagem
     */
    public Validation(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    /**
     * Gets .
     *
     * @return the
     */
    public String getcampo() {
        return campo;
    }

    /**
     * Sets .
     *
     * @param campo the campo
     */
    public void setcampo(String campo) {
        this.campo = campo;
    }

    /**
     * Gets .
     *
     * @return the
     */
    public String getmensagem() {
        return mensagem;
    }

    /**
     * Sets .
     *
     * @param mensagem the mensagem
     */
    public void setmensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Validation that = (Validation) o;

        if (campo != null ? !campo.equals(that.campo) : that.campo != null) return false;
        return !(mensagem != null ? !mensagem.equals(that.mensagem) : that.mensagem != null);

    }

    @Override
    public int hashCode() {
        int result = campo != null ? campo.hashCode() : 0;
        result = 31 * result + (mensagem != null ? mensagem.hashCode() : 0);
        return result;
    }
    
}
