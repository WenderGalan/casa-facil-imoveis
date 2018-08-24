package com.casafacilimoveis.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: SenhaUtil.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
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
