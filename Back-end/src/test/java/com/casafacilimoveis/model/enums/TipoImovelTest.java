package com.casafacilimoveis.model.enums;

import com.casafacilimoveis.model.enums.TipoImovel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: TipoImovelTest.java
 * Criado por : Wender Galan
 * Data da criação : 03/09/2018
 * Observação :
 * *********************************************
 */
public class TipoImovelTest  {

    @Test
    public void enumTest() {
        assertEquals(0, TipoImovel.CASA.getCode());
        assertEquals(1, TipoImovel.APARTAMENTO.getCode());
        assertEquals(2, TipoImovel.LOJA.getCode());
        assertEquals(3, TipoImovel.TERRENO.getCode());
        assertEquals(4, TipoImovel.FAZENDA.getCode());
        assertEquals(5, TipoImovel.IMOVEL_COMERCIAL.getCode());
    }
}
