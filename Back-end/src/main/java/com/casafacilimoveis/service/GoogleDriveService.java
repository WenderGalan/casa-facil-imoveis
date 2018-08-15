package com.casafacilimoveis.service;

import com.google.api.services.drive.model.File;

/**
 * The interface Google drive service.
 */
public interface GoogleDriveService {

    /**
     * Upload file file.
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
