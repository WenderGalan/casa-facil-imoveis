package com.casafacilimoveis.model.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Favorito.java
 * Criado por : Wender Galan
 * Data da criação : 07/11/2018
 * Observação :
 * *********************************************
 */
@Entity
@Table(name = "favorito", schema = "public")
@Data
@Builder
@EqualsAndHashCode
@ToString
public class Favorito implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_favorito")
    @SequenceGenerator(name = "seq_favorito", sequenceName = "favorito_id_seq", schema = "public", allocationSize = 1)
    private Integer id;

    @JoinColumn(referencedColumnName = "id", name = "id_anuncio")
    @ManyToOne(fetch = FetchType.EAGER)
    private Anuncio anuncio;

    @JoinColumn(referencedColumnName = "id", name = "id_anunciante")
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
}
