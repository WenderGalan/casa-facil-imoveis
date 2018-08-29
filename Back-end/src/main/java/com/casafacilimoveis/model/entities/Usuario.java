package com.casafacilimoveis.model.entities;

import com.casafacilimoveis.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: Usuario.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
@Entity
@Table(name = "usuarios", schema = "public")
public class Usuario implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuarios")
    @SequenceGenerator(name = "seq_usuarios", sequenceName = "usuarios_id_seq", schema = "public", allocationSize = 1)
    private Integer id;

    @Column(name = "nome", nullable = false)
    @Length(min = 1, max = 100, message = "O nome deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O nome não foi informado.")
    private String nome;

    @Column(name = "email", nullable = false)
    @Length(min = 1, max = 200, message = "O email deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O email não foi informado.")
    @Email
    private String email;

    @Column(name = "numero")
    @Length(min = 1, max = 11, message = "O número deve ter no minímo {min} e no máximo {max} caracteres")
    private String numero;

    @Column(name = "senha", nullable = false)
    @Length(min = 1, max = 500, message = "A senha deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "A senha não foi informada.")
    private String senha;

    @Column(name = "tipo_usuario", nullable = false)
    @NotNull(message = "O tipo do usuário não foi informado.")
    private TipoUsuario tipoUsuario;

    //Imagem padrão dos usuários
    @Column(name = "url_imagem")
    private String urlImagem = "https://drive.google.com/uc?id=1NzqIM3Li0YlTZDcTkGrIDCQsFUsCZqgm";

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anunciante")
    private List<Anuncio> anuncios;

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
     * Gets nome.
     *
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets nome.
     *
     * @param nome the nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets numero.
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Sets numero.
     *
     * @param numero the numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Gets senha.
     *
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Sets senha.
     *
     * @param senha the senha
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Gets tipo usuario.
     *
     * @return the tipo usuario
     */
    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Sets tipo usuario.
     *
     * @param tipoUsuario the tipo usuario
     */
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Gets url imagem.
     *
     * @return the url imagem
     */
    public String getUrlImagem() {
        return urlImagem;
    }

    /**
     * Sets url imagem.
     *
     * @param urlImagem the url imagem
     */
    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
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
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(numero, usuario.numero) &&
                Objects.equals(senha, usuario.senha) &&
                tipoUsuario == usuario.tipoUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, numero, senha, tipoUsuario);
    }
}
