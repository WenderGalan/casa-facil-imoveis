package com.casafacilimoveis.service.impl;

import com.casafacilimoveis.model.beans.Validation;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.ContatoAnunciante;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: EmailServiceImpl.java
 * Criado por : Wender Galan
 * Data da criação : 25/09/2018
 * Observação :
 * *********************************************
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public ResponseEntity sendEmailConfirmacao(String nome, String email) {
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

    @Override
    public ResponseEntity sendEmailContato(ContatoAnunciante contato, Integer idAnuncio) {
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

    @Override
    public ResponseEntity sendEmailWithAttachement(String arquivo, String to, String subject, String text) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            FileSystemResource file = new FileSystemResource(arquivo);
            helper.addAttachment(file.getFilename(), file);
            helper.setText(text);
            helper.setSubject(subject);
            helper.setTo(to);
            mailSender.send(message);
            //Deleta o arquivo do servidor
            File fileDeletar = new File(arquivo);
            fileDeletar.delete();
            return ResponseEntity.ok("Relatório gerado com sucesso!\nEm instantes irá recebê-lo em seu e-mail cadastrado!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
