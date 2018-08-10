package com.casafacilimoveis.controller;

import org.springframework.web.bind.annotation.*;

/**
 * The type Servidor controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/servidor")
public class ServidorController {


    /**
     * PODE APAGAR ESTA CLASSE, É APENAS PARA TESTES
     *
     * @param nome the nome
     * @return the string
     */
    @GetMapping("/salvar")
    public String salvar(@RequestParam("nome") String nome) {

        return "Deu tudo certo " +  nome;
    }
}
