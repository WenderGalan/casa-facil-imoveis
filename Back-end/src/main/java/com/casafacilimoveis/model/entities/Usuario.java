package com.casafacilimoveis.model.entities;

import com.casafacilimoveis.model.enums.TipoUsuario;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: Usuario.java
 * Criado por : Wender Galan
 * Data da criação : 07/11/2018
 * Observação :
 * *********************************************
 */
@Entity
@Table(name = "usuario", schema = "public")
public class Usuario implements Serializable {
    public static final long serialVersionUID = 1L;

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
}
