package com.casafacilimoveis.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@NoArgsConstructor
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
}
