package com.casafacilimoveis.model.entities;

import lombok.Data;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.Serializable;
import java.util.List;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Relatorio.java
 * Criado por : Wender Galan
 * Data da criação : 29/10/2018
 * Observação :
 * *********************************************
 */
@Data
public class Relatorio implements Serializable {

    private static final long serialVersionUID = 2405172041950251807L;

    private JRBeanCollectionDataSource data0;
    private JRBeanCollectionDataSource data1;
    private JRBeanCollectionDataSource data2;

    public Relatorio(List data) {
        this.data0 = new JRBeanCollectionDataSource(data);
        this.data1 = new JRBeanCollectionDataSource(data);
        this.data2 = new JRBeanCollectionDataSource(data);
    }
}
