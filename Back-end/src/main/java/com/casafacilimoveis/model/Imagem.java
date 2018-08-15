package com.casafacilimoveis.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The type Imagem.
 */
@Entity
@Table(name = "imagem", schema = "public")
public class Imagem implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "imagem_url", nullable = false)
    private String imagemUrl;

    //todo FALTA A FK DO ANUNCIO

    public Imagem() {
    }

    public Imagem(String imagemUrl) {
        this.id = imagemUrl;
        this.imagemUrl = "https://drive.google.com/uc?id=" + imagemUrl;
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
}


