package com.casafacilimoveis.model.entities;

import com.casafacilimoveis.model.enums.TipoUsuario;
import lombok.*;
import lombok.experimental.SuperBuilder;
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
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario", schema = "public")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorValue(value = "0")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "nome", nullable = false)
    @Length(min = 1, max = 100, message = "O nome deve ter no minímo {min} e no máximo {max} caracteres")
    @NotNull(message = "O nome não foi informado.")
    private String nome;

    @Column(name = "dtype", nullable = false)
    private Integer dtype;

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
}
