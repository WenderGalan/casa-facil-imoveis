package com.casafacilimoveis.service;

import com.casafacilimoveis.model.entities.ContatoAnunciante;
import com.casafacilimoveis.model.enums.TipoNegocio;
import org.springframework.http.ResponseEntity;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: EmailService.java
 * Criado por : Wender Galan
 * Data da criação : 25/09/2018
 * Observação :
 * *********************************************
 */
public interface EmailService {

    public ResponseEntity sendEmailConfirmacao(String nome, String email);

    public ResponseEntity sendEmailContato(ContatoAnunciante contato, Integer idAnuncio);

    public ResponseEntity sendEmailWithAttachement(String arquivo, String to, String subject, String text);
}
