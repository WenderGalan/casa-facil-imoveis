package com.casafacilimoveis.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * Nome do arquivo: Endereco.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
@Entity
@Table(name = "endereco", schema = "public")
public class Endereco implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
    @SequenceGenerator(name = "seq_endereco", sequenceName = "endereco_id_seq", schema = "public", allocationSize = 1)
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
    @Length(min = 1, max = 100, message = "O estado deve ter no minímo {min} e no máximo {max} caracteres")
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

    /**
     * Instantiates a new Endereco.
     */
    public Endereco() {
    }

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
     * Gets endereco.
     *
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Sets endereco.
     *
     * @param endereco the endereco
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Gets complemento.
     *
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Sets complemento.
     *
     * @param complemento the complemento
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * Gets bairro.
     *
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Sets bairro.
     *
     * @param bairro the bairro
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Gets numero.
     *
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * Sets numero.
     *
     * @param numero the numero
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * Gets estado.
     *
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Sets estado.
     *
     * @param estado the estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Gets cidade.
     *
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Sets cidade.
     *
     * @param cidade the cidade
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Gets cep.
     *
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * Sets cep.
     *
     * @param cep the cep
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Gets latitude.
     *
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * Sets latitude.
     *
     * @param latitude the latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets longitude.
     *
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * Sets longitude.
     *
     * @param longitude the longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets anuncios.
     *
     * @return the anuncios
     */
    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    /**
     * Sets anuncios.
     *
     * @param anuncios the anuncios
     */
    public void setAnuncios(List<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco1 = (Endereco) o;
        return Objects.equals(id, endereco1.id) &&
                Objects.equals(endereco, endereco1.endereco) &&
                Objects.equals(complemento, endereco1.complemento) &&
                Objects.equals(bairro, endereco1.bairro) &&
                Objects.equals(numero, endereco1.numero) &&
                Objects.equals(estado, endereco1.estado) &&
                Objects.equals(cidade, endereco1.cidade) &&
                Objects.equals(cep, endereco1.cep) &&
                Objects.equals(latitude, endereco1.latitude) &&
                Objects.equals(longitude, endereco1.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endereco, complemento, bairro, numero, estado, cidade, cep, latitude, longitude);
    }
}
