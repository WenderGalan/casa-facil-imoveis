package com.casafacilimoveis.model.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: ContatoAnunciante.java
 * Criado por : Wender Galan
 * Data da criação : 10/09/2018
 * Observação :
 * *********************************************
 */
public class ContatoAnunciante {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String mensagem;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
