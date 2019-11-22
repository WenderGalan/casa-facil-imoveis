package com.casafacilimoveis.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

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
@DiscriminatorValue(value = "2")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"anuncios"}, callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Anunciante extends Usuario {

    @CNPJ
    private String cnpj;

    private String creci;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anunciante")
    private List<Anuncio> anuncios;
}
