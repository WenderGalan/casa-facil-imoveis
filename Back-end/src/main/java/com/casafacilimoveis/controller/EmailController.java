package com.casafacilimoveis.controller;

import com.casafacilimoveis.model.beans.Validation;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.ContatoAnunciante;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.repository.UsuarioRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

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
    private JavaMailSender mailSender;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

    @ApiOperation("Envio de e-mail para contato com o anunciante")
    @PostMapping("v1/email-enviar-contato/{id}")
    public ResponseEntity sendEmailContato(@RequestBody ContatoAnunciante contato, @PathVariable("id") Integer idAnuncio) {
        if (!contato.getMensagem().isEmpty() && !contato.getNome().isEmpty() && !contato.getEmail().isEmpty() && idAnuncio != null) {
            Anuncio anuncio = anuncioRepository.findOneById(idAnuncio);
            if (anuncio != null) {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setText(contato.getMensagem() + "\n\n" + "Informações para contato:"
                        + "\nNome: " + contato.getNome() + "\nEmail: " + contato.getEmail());
                message.setSubject("Contato referente ao anúncio " + anuncio.getTitulo());
                message.setTo(anuncio.getAnunciante().getEmail());
                try {
                    mailSender.send(message);
                    return ResponseEntity.ok().build();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (e instanceof MailParseException) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                    } else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
