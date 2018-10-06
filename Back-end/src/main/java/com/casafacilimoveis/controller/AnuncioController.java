package com.casafacilimoveis.controller;

import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.enums.TipoNegocio;
import com.casafacilimoveis.service.AnuncioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    private AnuncioService anuncioService;

    /**
     * Buscar todos response entity.
     *
     * @param page the page
     * @param size tamanho da pagina
     * @param id   the id to search
     * @return the response entity
     */
    @ApiOperation("Busca todos os anúncios da aplicação")
    @GetMapping("/v1")
    public ResponseEntity buscarTodos(@RequestParam(value = "id", required = false) Integer id,
                                      @RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "size") Integer size) {
        return anuncioService.buscarTodos(id, page, size);
    }


    /**
     * Buscar todos os anúncios by rua, bairro e cidade
     *
     * @return List of anuncios
     * @
     */
    @ApiOperation("Busca todos os anúncios com os parâmetros passados")
    @GetMapping("/v1/search")
    public ResponseEntity buscarTodosPorParametros(
            @RequestParam(value = "rua", required = false) String rua,
            @RequestParam(value = "bairro", required = false) String bairro,
            @RequestParam(value = "cidade", required = false) String cidade,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size) {
        return anuncioService.buscarTodosPorParametros(rua, bairro, cidade, page, size);
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
        return anuncioService.buscarPorId(id);
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
        return anuncioService.salvar(anuncio, id, result);
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
        return anuncioService.alterar(anuncio, result);
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
        return anuncioService.excluirPorId(id);
    }

    /**
     * Cria o relátorio da listagem de imoveis a venda
     *
     * @param idUsuario   - id do usuário logado
     * @param tipoNegocio - é o tipo de negócio que será baseado o relatorio
     * @return relatorio
     **/
    @ApiOperation("Gera o relatório de listagem de anúncios a venda ou para alugar do usuário")
    @GetMapping("/relatorio/venda/{idUsuario}")
    public ResponseEntity listagemVendaAluguel(@PathVariable("idUsuario") Integer idUsuario, @RequestParam TipoNegocio tipoNegocio, HttpServletResponse response) {
        return anuncioService.relatorioVendaAluguel(idUsuario, tipoNegocio, response);
    }

}
