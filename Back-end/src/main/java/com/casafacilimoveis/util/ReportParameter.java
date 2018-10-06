package com.casafacilimoveis.util;

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
public class ReportParameter {

    private String key;
    private Object value;

    public ReportParameter(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}