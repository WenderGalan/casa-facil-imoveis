package com.casafacilimoveis.model.entities;

import com.casafacilimoveis.model.enums.TipoImovel;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "anuncios", schema = "public")
public class Anuncio implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_anuncio")
    @SequenceGenerator(name = "seq_anuncio", sequenceName = "anuncio_id_seq", schema = "public", allocationSize = 1)
    private Integer id;

    @Column(name = "titulo", nullable = false)
    @Length(min = 1, max = 200, message = "O titulo deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O titulo não foi informado.")
    private String titulo;

    @Column(name = "descricao", nullable = false)
    @Length(min = 1, max = 1000, message = "A descrição deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O descrição não foi informado.")
    private String descricao;

    @Column(name = "valor", nullable = false)
    @NotNull(message = "O valor não foi informado.")
    private Double valor;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_anunciante", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_anuncio_anunciante"))
    private Usuario anunciante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_anuncio_endereco"))
    private Endereco endereco;

    @Column(name = "tipo_imovel", nullable = false)
    @NotNull(message = "O tipo de imóvel não foi informado.")
    private TipoImovel tipoImovel;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "anuncio")
    private List<Imagem> imagensAnuncios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Usuario getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Usuario anunciante) {
        this.anunciante = anunciante;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    public List<Imagem> getImagensAnuncios() {
        return imagensAnuncios;
    }

    public void setImagensAnuncios(List<Imagem> imagensAnuncios) {
        this.imagensAnuncios = imagensAnuncios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anuncio anuncio = (Anuncio) o;
        return Objects.equals(id, anuncio.id) &&
                Objects.equals(titulo, anuncio.titulo) &&
                Objects.equals(descricao, anuncio.descricao) &&
                Objects.equals(valor, anuncio.valor) &&
                Objects.equals(anunciante, anuncio.anunciante) &&
                Objects.equals(endereco, anuncio.endereco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, valor, anunciante, endereco);
    }
}
