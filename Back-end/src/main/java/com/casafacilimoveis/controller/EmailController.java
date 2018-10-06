package com.casafacilimoveis.controller;

import com.casafacilimoveis.model.entities.ContatoAnunciante;
import com.casafacilimoveis.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: EmailController.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Api(description = "Controller de requisições de email")
@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    /**
     * Send email response entity.
     *
     * @param nome  the nome
     * @param email the email
     * @return the string
     */
    @ApiOperation("Envio de e-mail para confirmar o código de cadastro")
    @GetMapping(path = "/v1/email-enviar-codigo")
    public ResponseEntity sendEmailConfirmacao(@RequestParam String nome, @RequestParam String email) {
        return emailService.sendEmailConfirmacao(nome, email);
    }

    @ApiOperation("Envio de e-mail para contato com o anunciante")
    @PostMapping("v1/email-enviar-contato/{id}")
    public ResponseEntity sendEmailContato(@RequestBody ContatoAnunciante contato, @PathVariable("id") Integer idAnuncio) {
        return emailService.sendEmailContato(contato, idAnuncio);
    }
}
