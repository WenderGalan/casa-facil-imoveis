package com.casafacilimoveis.util;

import lombok.Builder;
import lombok.Data;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: ReportParameter.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Data
@Builder
public class ReportParameter {
    private String key;
    private Object value;
}