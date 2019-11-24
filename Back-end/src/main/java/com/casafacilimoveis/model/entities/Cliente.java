package com.casafacilimoveis.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"anunciosFavoritos"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Cliente extends Usuario {

    @CPF
    private String cpf;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<Favorito> anunciosFavoritos;
}
