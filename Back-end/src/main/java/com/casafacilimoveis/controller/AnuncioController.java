package com.casafacilimoveis.controller;

import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.enums.TipoNegocio;
import com.casafacilimoveis.model.enums.TipoRelatorio;
import com.casafacilimoveis.model.enums.TipoTemplate;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.rsql.CustomRsqlVisitor;
import com.casafacilimoveis.service.AnuncioService;
import com.casafacilimoveis.util.Constantes;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
@CacheConfig(cacheNames = {Constantes.CACHE_ANUNCIOS}) //Diz ao Spring boot onde salvar o cache
@RestController
@CrossOrigin
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioRepository anuncioRepository;

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
    @Cacheable
    @GetMapping("/v1")
    public ResponseEntity buscarTodos(@RequestParam(value = "id", required = false) Integer id,
                                      @RequestParam(value = "page") Integer page,
                                      @RequestParam(value = "size") Integer size) {
        return anuncioService.buscarTodos(id, page, size);
    }

    /**
     * Busca as possiveis autocomplete
     *
     * @param text - texto que sera base da pesquisa
     * @return uma lista de Auto Completes
     **/
    @ApiOperation("Busca todos os possíveis autocomplete para o campo desejado")
    @Cacheable
    @GetMapping("/v1/autocomplete")
    public ResponseEntity buscaTodosAutoComplete(@RequestParam(value = "pesquisa") String text) {
        return anuncioService.buscaTodosAutoComplete(text);
    }

    /**
     * Buscar todos os anúncios by rua, bairro e cidade
     *
     * @param page     - pagina selecionada
     * @param size     - tamanho da pagina selecionada
     * @param pesquisa - texto para ser base da pesquisa
     * @return List of anuncios
     */
    @ApiOperation("Busca todos os anúncios com os parâmetros passados")
    @Cacheable
    @GetMapping("/v1/search")
    public ResponseEntity buscarTodosPorParametros(
            @RequestParam(value = "pesquisa", defaultValue = "") String pesquisa,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size) {
        Node rootNode = new RSQLParser().parse(pesquisa);
        Specification<Anuncio> spec = rootNode.accept(new CustomRsqlVisitor<>());

        PageRequest pageable = PageRequest.of(page, size);

        Page<Anuncio> anuncios = ((JpaSpecificationExecutor) anuncioRepository).findAll(spec, pageable);

        return ResponseEntity.ok(anuncios.getContent());
        //return anuncioService.buscarTodosPorParametros(pesquisa, page, size);
    }


    /**
     * Buscar por id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @ApiOperation("Busca apenas um anúncio pelo o ID")
    @Cacheable
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
    @CacheEvict(value = Constantes.CACHE_ANUNCIOS, allEntries = true)
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
    @CacheEvict(value = Constantes.CACHE_ANUNCIOS, allEntries = true)
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
    @CacheEvict(value = Constantes.CACHE_ANUNCIOS, allEntries = true)
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
    public ResponseEntity listagemVendaAluguel(@PathVariable("idUsuario") Integer idUsuario, @RequestParam TipoNegocio tipoNegocio, @RequestParam TipoRelatorio tipoRelatorio, @RequestParam TipoTemplate tipoTemplate) {
        return anuncioService.relatorioVendaAluguel(idUsuario, tipoNegocio, tipoRelatorio, tipoTemplate);
    }

}
