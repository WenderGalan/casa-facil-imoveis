package com.casafacilimoveis.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Cliente.java
 * Criado por : Wender Galan
 * Data da criação : 07/11/2018
 * Observação :
 * *********************************************
 */
@Entity
@DiscriminatorValue(value = "1")
public class Cliente extends Usuario {

    @CPF
    private String cpf;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Favorito> anunciosFavoritos;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Favorito> getAnunciosFavoritos() {
        return anunciosFavoritos;
    }

    public void setAnunciosFavoritos(List<Favorito> anunciosFavoritos) {
        this.anunciosFavoritos = anunciosFavoritos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf) &&
                Objects.equals(anunciosFavoritos, cliente.anunciosFavoritos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf, anunciosFavoritos);
    }
}
