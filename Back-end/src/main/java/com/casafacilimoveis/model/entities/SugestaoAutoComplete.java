package com.casafacilimoveis.model.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: SugestaoAutoComplete.java
 * Criado por : Wender Galan
 * Data da criação : 26/09/2018
 * Observação :
 * *********************************************
 */
public class SugestaoAutoComplete {

    String pesquisa;
    String concatenacao;

    public SugestaoAutoComplete(String pesquisa, String concatenacao) {
        this.pesquisa = pesquisa;
        this.concatenacao = concatenacao;
    }

    public SugestaoAutoComplete() {
    }

    public String getPesquisa() {
        return pesquisa;
    }

    public void setPesquisa(String pesquisa) {
        this.pesquisa = pesquisa;
    }

    public String getConcatenacao() {
        return concatenacao;
    }

    public void setConcatenacao(String concatenacao) {
        this.concatenacao = concatenacao;
    }

    public static List<SugestaoAutoComplete> criaSugestaoAutoComplete(List<String> cidades, List<String> bairros, List<String> enderecos) {
        List<SugestaoAutoComplete> sugestaoAutoCompleteList = new ArrayList<>();
        if (cidades != null && cidades.size() > 0) {
            for (String cidade : cidades) {
                String [] cidadeSplit = cidade.split(" - ");
                sugestaoAutoCompleteList.add(new SugestaoAutoComplete(cidadeSplit[0], cidade));
            }
        }
        if (bairros != null && bairros.size() > 0) {
            for (String bairro : bairros) {
                String [] bairroSplit = bairro.split(" - ");
                sugestaoAutoCompleteList.add(new SugestaoAutoComplete(bairroSplit[0], bairro));
            }
        }
        if (enderecos != null && enderecos.size() > 0){
            for (String endereco : enderecos){
                String [] enderecoSplit = endereco.split(" - ");
                sugestaoAutoCompleteList.add(new SugestaoAutoComplete(enderecoSplit[0], endereco));
            }
        }
        return sugestaoAutoCompleteList;
    }
}
