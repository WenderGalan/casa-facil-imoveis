package com.casafacilimoveis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Email controller.
 *
 * O email que faz o envio de e-mail tem as seguintes configurações:
 *
 * Usuario: casafacilimoveisconfirmation@gmail.com
 * Senha: l3e8r5h6g2
 */
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;


    /**
     * CONFIGURAR O ENVIO DE EMAIL AINDA
     *
     * @return the string
     */
    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setText("Aqui vai o corpo <strong>da</strong> mensagem");
        message.setSubject("Aqui vai o titulo do email");
        message.setTo("wendergalan2014@hotmail.com");

        try {
            mailSender.send(message);
            return "Email enviado com sucesso!";
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof MailParseException) {
                return "O e-mail é inválido!";
            }
            return "Erro ao envia o email!";
        }

    }
}
