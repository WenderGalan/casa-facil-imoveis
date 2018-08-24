package com.casafacilimoveis.service.impl;

import com.casafacilimoveis.service.GoogleDriveService;
import com.casafacilimoveis.util.Constantes;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: GoogleDriveServiceImpl.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
@Service
public class GoogleDriveServiceImp implements GoogleDriveService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDriveServiceImp.class);

    @Value("${google.service_account_email}")
    private String serviceAccountEmail;

    @Value("${google.application_name}")
    private String applicationName;

    @Value("${google.service_account_key}")
    private String serviceAccountKey;

    @Value("${google.folder_id}")
    private String folderID;

    /**
     * Gets drive service.
     *
     * @return the drive service
     */
    public Drive getDriveService() {
        Drive service = null;
        try {
            URL resource = GoogleDriveServiceImp.class.getResource("/" + this.serviceAccountKey);
            java.io.File key = Paths.get(resource.toURI()).toFile();
            HttpTransport httpTransport = new NetHttpTransport();
            JacksonFactory jsonFactory = new JacksonFactory();

            GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
                    .setJsonFactory(jsonFactory).setServiceAccountId(serviceAccountEmail)
                    .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
                    .setServiceAccountPrivateKeyFromP12File(key).build();

            service = new Drive.Builder(httpTransport, jsonFactory, credential).setApplicationName(applicationName)
                    .setHttpRequestInitializer(credential).build();

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return service;
    }

    /**
     * @param file
     * Este método faz o upload de arquivo para a pasta no drive.
     */
    @Override
    public File uploadFile(java.io.File file) {
        File fileGoogle = new File();
        try {
            File fileMetadata = new File();
            fileMetadata.setMimeType(Constantes.MIME_TYPE_IMAGE);
            fileMetadata.setName(file.getName());
            fileMetadata.setParents(Collections.singletonList(folderID));
            FileContent fileContent = new FileContent(Constantes.MIME_TYPE_IMAGE, file);
            fileGoogle = getDriveService().files().create(fileMetadata, fileContent).setFields("id,webContentLink,webViewLink").execute();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }
        return fileGoogle;
    }

    /**
     * @param idFile é o ID da imagem salva no banco de dados
     *               Este método deleta a imagem da pasta para não deixar muitos itens salvos lá atoa.
     */
    @Override
    public boolean deleteFile(String idFile) {
        try {
            getDriveService().files().delete(idFile).execute();
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
