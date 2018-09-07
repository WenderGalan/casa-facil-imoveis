package com.casafacilimoveis.model.entities;

import com.casafacilimoveis.model.enums.TipoImovel;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: Anuncio.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
@Entity
@Table(name = "anuncios", schema = "public")
public class Anuncio implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_anuncio")
    @SequenceGenerator(name = "seq_anuncio", sequenceName = "anuncio_id_seq", schema = "public", allocationSize = 1)
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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_anunciante", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_anuncio_anunciante"))
    private Usuario anunciante;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_anuncio_endereco"))
    private Endereco endereco;

    @Column(name = "tipo_imovel", nullable = false)
    @NotNull(message = "O tipo de imóvel não foi informado.")
    private TipoImovel tipoImovel;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "anuncio")
    private List<Imagem> imagensAnuncios;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets titulo.
     *
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets titulo.
     *
     * @param titulo the titulo
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Gets descricao.
     *
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets descricao.
     *
     * @param descricao the descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Gets valor.
     *
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * Sets valor.
     *
     * @param valor the valor
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * Gets anunciante.
     *
     * @return the anunciante
     */
    public Usuario getAnunciante() {
        return anunciante;
    }

    /**
     * Sets anunciante.
     *
     * @param anunciante the anunciante
     */
    public void setAnunciante(Usuario anunciante) {
        this.anunciante = anunciante;
    }

    /**
     * Gets endereco.
     *
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * Sets endereco.
     *
     * @param endereco the endereco
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Gets tipo imovel.
     *
     * @return the tipo imovel
     */
    public TipoImovel getTipoImovel() {
        return tipoImovel;
    }

    /**
     * Sets tipo imovel.
     *
     * @param tipoImovel the tipo imovel
     */
    public void setTipoImovel(TipoImovel tipoImovel) {
        this.tipoImovel = tipoImovel;
    }

    /**
     * Gets imagens anuncios.
     *
     * @return the imagens anuncios
     */
    public List<Imagem> getImagensAnuncios() {
        return imagensAnuncios;
    }

    /**
     * Sets imagens anuncios.
     *
     * @param imagensAnuncios the imagens anuncios
     */
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
