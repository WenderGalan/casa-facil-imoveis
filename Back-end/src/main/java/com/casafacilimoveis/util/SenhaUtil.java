package com.casafacilimoveis.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The type Senha utils.
 * @author Wender Galan
 * Em 09/08/2018
 *
 * Exemplo de utilização:
 * String senhaEncoded = SenhaUtil.gerarBCrypt("senhaDoUsuario");
 * boolean senhaValida = SenhaUtil.senhaValida("senhaDoUsuario", senhaEncoded);
 *
 */
public class SenhaUtil {

    /**
     * Gera um hash utilizando o Bcrypt
     *
     * @param senha the senha
     * @return the string
     */
    public static String gerarBCrypt(String senha){
        if (senha == null){
            return senha;
        }

        BCryptPasswordEncoder bCryptEncoder =  new BCryptPasswordEncoder();
        return bCryptEncoder.encode(senha);
    }

    /**
     * Verifica se a senha é válida
     *
     * @param senha        the senha
     * @param senhaEncoded the senha encoded
     * @return the boolean
     */
    public static boolean senhaValida(String senha, String senhaEncoded){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(senha, senhaEncoded);
    }
}
