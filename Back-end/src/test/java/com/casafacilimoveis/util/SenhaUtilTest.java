package com.casafacilimoveis.util;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class SenhaUtilTest {

    private static final String SENHA = "123456";
    private final BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

    @Test
    public void testSenhaNula() throws Exception {
        assertNull(SenhaUtil.gerarBCrypt(null));
    }

    @Test
    public void testGerarHashSenha() throws Exception {
        String hash = SenhaUtil.gerarBCrypt(SENHA);

        assertTrue(bCryptEncoder.matches(SENHA, hash));
    }

    @Test
    public void testSenhaIgual() throws Exception {
        String senhaCriptografada = SenhaUtil.gerarBCrypt(SENHA);
        assertTrue(SenhaUtil.senhaValida(SENHA, senhaCriptografada));
    }
}
