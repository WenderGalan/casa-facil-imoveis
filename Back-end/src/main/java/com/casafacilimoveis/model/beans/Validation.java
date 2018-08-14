package com.casafacilimoveis.model.beans;

public class Validation {

    private String campo;
    private String mensagem;

    public Validation() {
    }

    public Validation(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getcampo() {
        return campo;
    }

    public void setcampo(String campo) {
        this.campo = campo;
    }

    public String getmensagem() {
        return mensagem;
    }

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
