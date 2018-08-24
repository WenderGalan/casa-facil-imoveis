package com.casafacilimoveis.service;

import com.google.api.services.drive.model.File;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: GoogleDriveService.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
public interface GoogleDriveService {

    /**
     * Upload file file.
     *
     * @param file the file
     * @return the file
     */
    public File uploadFile(java.io.File file);

    /**
     * Delete file boolean.
     *
     * @param idFile the id file
     * @return the boolean
     */
    public boolean deleteFile(String idFile);
}
