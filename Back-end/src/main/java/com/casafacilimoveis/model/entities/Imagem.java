package com.casafacilimoveis.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The type Imagem.
 */
@Entity
@Table(name = "imagens", schema = "public")
public class Imagem implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "imagem_url", nullable = false)
    private String imagemUrl;

    @JsonIgnore
    @NotNull
    @JoinColumn(referencedColumnName = "id", name = "id_anuncio", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Anuncio anuncio;

    public Imagem() {
    }

    public Imagem(String imagemUrl, Anuncio anuncio) {
        this.id = imagemUrl;
        this.imagemUrl = "https://drive.google.com/uc?id=" + imagemUrl;
        this.anuncio = anuncio;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets imagem url.
     *
     * @return the imagem url
     */
    public String getImagemUrl() {
        return imagemUrl;
    }

    /**
     * Sets imagem url concatenando com o resto da URL do Google drive;
     *
     * @param imagemUrl the imagem url
     */
    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = "https://drive.google.com/uc?id=" + imagemUrl;
    }

    public Anuncio getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Anuncio anuncio) {
        this.anuncio = anuncio;
    }
}


