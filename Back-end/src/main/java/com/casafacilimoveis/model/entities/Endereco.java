package com.casafacilimoveis.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    public Endereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
