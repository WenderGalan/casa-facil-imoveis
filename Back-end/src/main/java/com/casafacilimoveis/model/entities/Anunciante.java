package com.casafacilimoveis.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Anunciante.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Entity
public class Anunciante extends Usuario {

    @CNPJ
    private String cnpj;

    private String creci;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anunciante")
    private List<Anuncio> anuncios;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCreci() {
        return creci;
    }

    public void setCreci(String creci) {
        this.creci = creci;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anunciante that = (Anunciante) o;
        return Objects.equals(cnpj, that.cnpj) &&
                Objects.equals(creci, that.creci) &&
                Objects.equals(anuncios, that.anuncios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cnpj, creci, anuncios);
    }
}
