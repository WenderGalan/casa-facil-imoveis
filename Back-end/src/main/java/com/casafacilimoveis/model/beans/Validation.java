package com.casafacilimoveis.model.beans;

import lombok.Builder;
import lombok.Data;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Validation.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Data
@Builder
public class Validation {
    private String campo;
    private String mensagem;
}
