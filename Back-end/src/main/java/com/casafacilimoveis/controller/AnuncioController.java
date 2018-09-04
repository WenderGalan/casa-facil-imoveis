package com.casafacilimoveis.controller;

import com.casafacilimoveis.model.beans.ResponseError;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Imagem;
import com.casafacilimoveis.model.entities.Usuario;
import com.casafacilimoveis.model.enums.CodeError;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.repository.UsuarioRepository;
import com.casafacilimoveis.service.GoogleDriveService;
import com.casafacilimoveis.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: AnuncioController.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Api(description = "Controller de requisições de anúncios")
@RestController
@CrossOrigin
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GoogleDriveService driveService;

    /**
     * Buscar todos response entity.
     *
     * @param id the id to search
     * @return the response entity
     */
    @ApiOperation("Busca todos os anúncios da aplicação")
    @GetMapping("/v1")
    public ResponseEntity buscarTodos(@RequestParam(value = "id", required = false) Integer id) {
        if (id != null && id != 0) {
            return ResponseEntity.ok(anuncioRepository.buscarTodosPorIdAnunciante(id));
        } else {
            return ResponseEntity.ok(anuncioRepository.findAll());
        }
    }

    /**
     * Buscar por id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation("Busca apenas um anúncio pelo o ID")
    @GetMapping("/v1/{id}")
    public ResponseEntity buscarPorId(@PathVariable("id") Integer id) {
        Anuncio anuncio = anuncioRepository.findOneById(id);

        if (anuncio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(anuncio);
    }

    /**
     * Salvar response entity.
     *
     * @param anuncio the anuncio
     * @param id      the id
     * @param result  the result
     * @return the response entity
     */
    @ApiOperation("Salva o anúncio")
    @PostMapping("/v1/{id}")
    public ResponseEntity salvar(@Valid @RequestBody Anuncio anuncio, @PathVariable("id") Integer id, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }

        Usuario usuario = usuarioRepository.findOneById(id);
        anuncio.setAnunciante(usuario);
        anuncioRepository.save(anuncio);

        return ResponseEntity.ok(anuncio);
    }

    /**
     * Alterar response entity.
     *
     * @param anuncio the anuncio
     * @param result  the result
     * @return the response entity
     */
    @ApiOperation("Altera o anúncio")
    @PutMapping("/v1")
    public ResponseEntity alterar(@Valid @RequestBody Anuncio anuncio, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Util.criarListaDeErrosDaValidacao(result.getAllErrors()));
        }

        anuncioRepository.save(anuncio);
        return ResponseEntity.ok(anuncio);
    }

    /**
     * Excluir por id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation("Exclui o anúncio pelo ID")
    @DeleteMapping("/v1/{id}")
    public ResponseEntity excluirPorId(@PathVariable("id") Integer id) {
        Anuncio anuncio = anuncioRepository.getOne(id);

        if (anuncio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        try {
            for (Imagem imagem : anuncio.getImagensAnuncios()) {
                if (!driveService.deleteFile(imagem.getId())) {
                    driveService.deleteFile(imagem.getId());
                }
            }
            anuncioRepository.delete(anuncio);
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(CodeError.NAO_PERMITIDO_EXCLUIR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
