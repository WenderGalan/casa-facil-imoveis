package com.casafacilimoveis.model.entities;

import lombok.Builder;
import lombok.Data;

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
@Data
@Builder
public class SugestaoAutoComplete {

    private String pesquisa;
    private String concatenacao;

    public static List<SugestaoAutoComplete> criaSugestaoAutoComplete(List<String> cidades, List<String> bairros, List<String> enderecos) {
        List<SugestaoAutoComplete> sugestaoAutoCompleteList = new ArrayList<>();
        if (cidades != null && !cidades.isEmpty()) {
            for (String cidade : cidades) {
                String[] cidadeSplit = cidade.split(" - ");
                sugestaoAutoCompleteList.add(SugestaoAutoComplete.builder().pesquisa(cidadeSplit[0]).concatenacao(cidade).build());
            }
        }
        if (bairros != null && !bairros.isEmpty()) {
            for (String bairro : bairros) {
                String[] bairroSplit = bairro.split(" - ");
                sugestaoAutoCompleteList.add(SugestaoAutoComplete.builder().pesquisa(bairroSplit[0]).concatenacao(bairro).build());
            }
        }
        if (enderecos != null && !enderecos.isEmpty()) {
            for (String endereco : enderecos) {
                String[] enderecoSplit = endereco.split(" - ");
                sugestaoAutoCompleteList.add(SugestaoAutoComplete.builder().pesquisa(enderecoSplit[0]).concatenacao(endereco).build());
            }
        }
        return sugestaoAutoCompleteList;
    }
}
