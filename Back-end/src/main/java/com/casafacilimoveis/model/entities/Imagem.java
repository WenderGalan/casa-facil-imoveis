package com.casafacilimoveis.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Imagem.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Entity
@Table(name = "imagem", schema = "public")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Imagem implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "imagem_url", nullable = false)
    private String imagemUrl;

    @JsonIgnore
    @JoinColumn(referencedColumnName = "id", name = "id_anuncio")
    @ManyToOne(fetch = FetchType.LAZY)
    private Anuncio anuncio;

    /**
     * Instantiates a new Imagem.
     *
     * @param imagemUrl the imagem url
     * @param anuncio   the anuncio
     */
    public Imagem(String imagemUrl, Anuncio anuncio) {
        this.id = imagemUrl;
        this.imagemUrl = "https://drive.google.com/uc?id=" + imagemUrl;
        this.anuncio = anuncio;
    }
}


