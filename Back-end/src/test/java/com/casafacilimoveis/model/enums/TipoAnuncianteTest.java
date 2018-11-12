package com.casafacilimoveis.model.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: TipoAnuncianteTest.java
 * Criado por : Wender Galan
 * Data da criação : 03/09/2018
 * Observação :
 * *********************************************
 */
public class TipoAnuncianteTest {

    @Test
    public void enumTest() {
        assertEquals(0, TipoUsuario.FISICA.getCode());
        assertEquals(2, TipoUsuario.JURIDICA.getCode());
    }
}
