package com.casafacilimoveis.model.entities;

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
 * Nome do arquivo: Endereco.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Entity
@Table(name = "endereco", schema = "public")
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = {"anuncios"})
@NoArgsConstructor
@AllArgsConstructor
public class Endereco implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
    @SequenceGenerator(name = "seq_endereco", sequenceName = "endereco_id_seq", schema = "public", allocationSize = 1)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "endereco", nullable = false)
    @Length(min = 1, max = 200, message = "O endereco deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O endereco não foi informado.")
    private String endereco;

    @Column(name = "complemento")
    @Length(min = 1, max = 100, message = "O complemento deve ter no minímo {min} e no máximo {max} caracteres")
    private String complemento;

    @Column(name = "bairro", nullable = false)
    @Length(min = 1, max = 100, message = "O bairro deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O bairro não foi informado.")
    private String bairro;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "estado", nullable = false)
    @Length(min = 1, max = 2, message = "O estado deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O estado não foi informado.")
    private String estado;

    @Column(name = "cidade", nullable = false)
    @Length(min = 1, max = 100, message = "O cidade deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O cidade não foi informado.")
    private String cidade; //localidade

    @Column(name = "cep", nullable = false)
    @Length(min = 1, max = 100, message = "O cep deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O cep não foi informado.")
    private String cep;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "endereco")
    private List<Anuncio> anuncios;
}
