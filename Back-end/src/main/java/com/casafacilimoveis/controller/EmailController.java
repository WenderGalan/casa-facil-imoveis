package com.casafacilimoveis.controller;

import com.casafacilimoveis.model.beans.Validation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.File;

import java.util.Random;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: EmailController.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
@Api(description = "Controller de requisições de email")
@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Send email response entity.
     *
     * @param nome  the nome
     * @param email the email
     * @return the string
     */
    @ApiOperation("Envio de e-mail para confirmar o código de cadastro")
    @GetMapping(path = "/v1/email-enviar-codigo")
    public ResponseEntity sendEmail(@RequestParam String nome, @RequestParam String email) {
        SimpleMailMessage message = new SimpleMailMessage();

        Integer codigo = new Random().nextInt(999999);

        message.setText("Olá " + nome + ",\n\nO código de confirmação da sua conta é:  " + codigo +
                "\n\nSe você não cadastrou este endereço de e-mail no casa fácil imóveis, por favor ignore esta mensagem." + "\n\nObrigado, \nEquipe Casa Fácil Imóveis.");
        message.setSubject("Código de verificação Casa Fácil Imóveis");
        message.setTo(email);

        Validation validation = new Validation("codigo", String.valueOf(codigo));

        try {
            mailSender.send(message);
            return ResponseEntity.ok(validation);
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MailParseException) {
                validation.setmensagem("O e-mail é inválido");
                return ResponseEntity.ok(validation);
            }
            validation.setmensagem("Erro ao envia o email");
            return ResponseEntity.ok(validation);
        }

    }
}
