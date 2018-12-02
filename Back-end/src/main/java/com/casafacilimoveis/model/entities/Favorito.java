package com.casafacilimoveis.model.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

    public Favorito() {
    }

    public Favorito(Anuncio anuncio, Cliente cliente) {
        this.anuncio = anuncio;
        this.cliente = cliente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorito favorito = (Favorito) o;
        return Objects.equals(id, favorito.id) &&
                Objects.equals(anuncio, favorito.anuncio) &&
                Objects.equals(cliente, favorito.cliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, anuncio, cliente);
    }
}
