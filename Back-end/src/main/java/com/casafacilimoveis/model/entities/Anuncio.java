package com.casafacilimoveis.model.entities;

import com.casafacilimoveis.model.enums.TipoImovel;
import com.casafacilimoveis.model.enums.TipoNegocio;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Anuncio.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Entity
@Table(name = "anuncio", schema = "public")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"favoritos"})
public class Anuncio implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_anuncio")
    @SequenceGenerator(name = "seq_anuncio", sequenceName = "anuncio_id_seq", schema = "public", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "titulo", nullable = false)
    @Length(min = 1, max = 50, message = "O titulo deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O titulo não foi informado.")
    private String titulo;

    @Column(name = "descricao", nullable = false)
    @Length(min = 1, max = 1000, message = "A descrição deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O descrição não foi informado.")
    private String descricao;

    @Column(name = "valor", nullable = false)
    @NotNull(message = "O valor não foi informado.")
    private Double valor;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_anunciante", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_anuncio_anunciante"))
    private Anunciante anunciante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_anuncio_endereco"))
    private Endereco endereco;

    @Column(name = "tipo_imovel", nullable = false)
    @NotNull(message = "O tipo de imóvel não foi informado.")
    private TipoImovel tipoImovel;

    @Column(name = "tipo_negocio", nullable = false)
    @NotNull(message = "O tipo do negócio não foi informado.")
    private TipoNegocio tipoNegocio;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "anuncio")
    private List<Imagem> imagensAnuncios;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anuncio")
    private List<Favorito> favoritos;
}
