package com.casafacilimoveis.controller;

import com.casafacilimoveis.service.ImagemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: ImagemController.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Api(description = "Controller de requisições de imagens")
@RestController
@CrossOrigin
@RequestMapping("/file")
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    /**
     * https://developers.google.com/drive/api/v2/reference/files/insert
     *
     * @param files arquivos para fazer upload
     * @param id    do anuncio para inserção no banco de dados
     * @return the response entity
     */
    @ApiOperation("Envio das imagens do anúncio")
    @PostMapping(path = "/v1/enviar-imagens-anuncio/{id}")
    public ResponseEntity salvarImagensAnuncio(@RequestParam("files") List<MultipartFile> files, @PathVariable("id") Integer id) {
        return imagemService.salvarImagensAnuncio(files, id);
    }

    /**
     * https://developers.google.com/drive/api/v2/reference/files/insert
     *
     * @param file arquivos para fazer upload
     * @param id   do anuncio para inserção no banco de dados
     * @return the response entity
     */
    @ApiOperation("Envio da imagem do perfil do usuário")
    @PostMapping(path = "/v1/enviar-imagem-user/{id}")
    public ResponseEntity salvarImagemUser(@RequestParam("file") MultipartFile file, @PathVariable("id") Integer id) {
        return imagemService.salvarImagemUser(file, id);
    }


    /**
     * Remove apenas um imagem do anúncio na edição do anúncio...
     **/
    @ApiOperation("Deleção de apenas uma imagem")
    @DeleteMapping("/v1/deletar-imagem-anuncio/{id}")
    public ResponseEntity deletarImagemAnuncio(@PathVariable("id") String id) {
        return imagemService.deletarImagemAnuncio(id);
    }

}
