package com.casafacilimoveis.model.enums;

import static com.casafacilimoveis.util.RelatorioUtil.*;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: TipoTemplate.java
 * Criado por : Wender Galan
 * Data da criação : 29/10/2018
 * Observação :
 * *********************************************
 */
public enum TipoTemplate {

    CABECALHO_CORPO_RODAPE(CABECALHO, CORPO, RODAPE),
    RODAPE_CABECALHO_CORPO(RODAPE, CABECALHO, CORPO),
    CABECALHO_RODAPE(CABECALHO, RODAPE);

    private final String descricao;
    private final String[] ordem;

    TipoTemplate(String... ordem) {
        this.ordem = ordem;
        String descricao = "";
        for (int i = 0; i < ordem.length; i++) {
            if (i > 0) {
                descricao += ", ";
            }
            descricao += ordem[i];
        }
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String[] getOrdem() {
        return ordem;
    }
}
