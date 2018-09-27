package com.casafacilimoveis.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: ImagemService.java
 * Criado por : Wender Galan
 * Data da criação : 25/09/2018
 * Observação :
 * *********************************************
 */
public interface ImagemService {

    public ResponseEntity deletarImagemAnuncio(String id);

    public ResponseEntity salvarImagemUser(MultipartFile file, Integer id);

    public ResponseEntity salvarImagensAnuncio(List<MultipartFile> files, Integer id);
}
