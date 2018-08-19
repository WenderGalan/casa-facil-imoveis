package com.casafacilimoveis.model.entities;

import com.casafacilimoveis.model.enums.TipoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "url_imagem")
    private String urlImagem;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "anunciante")
    private List<Anuncio> anuncios;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
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
