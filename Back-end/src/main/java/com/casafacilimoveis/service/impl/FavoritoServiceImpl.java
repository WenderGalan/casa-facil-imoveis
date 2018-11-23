package com.casafacilimoveis.service.impl;

import com.casafacilimoveis.model.beans.ResponseError;
import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Cliente;
import com.casafacilimoveis.model.entities.Favorito;
import com.casafacilimoveis.model.enums.CodeError;
import com.casafacilimoveis.repository.AnuncioRepository;
import com.casafacilimoveis.repository.ClienteRepository;
import com.casafacilimoveis.repository.FavoritoRepository;
import com.casafacilimoveis.service.FavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: FavoritoServiceImpl.java
 * Criado por : Wender Galan
 * Data da criação : 11/11/2018
 * Observação :
 * *********************************************
 */
@Service
public class FavoritoServiceImpl implements FavoritoService {

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Override
    public ResponseEntity salvar(Integer idCliente, Integer idAnuncio) {
        Cliente cliente = clienteRepository.getOne(idCliente);
        Anuncio anuncio = anuncioRepository.getOne(idAnuncio);

        Favorito favoritoExistente = favoritoRepository.findByClienteAndAnuncio(idCliente, idAnuncio);

        if (favoritoExistente == null) {
            Favorito favorito = new Favorito(anuncio, cliente);
            favoritoRepository.save(favorito);
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity excluirPorId(Integer id) {

        Favorito favorito = favoritoRepository.getOne(id);

        try {
            favoritoRepository.delete(favorito);
        } catch (Exception ex) {
            return new ResponseEntity(new ResponseError(CodeError.NAO_PERMITIDO_EXCLUIR, ex.getMessage()), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity buscarTodosPorCliente(Integer cliente) {
        return ResponseEntity.ok(favoritoRepository.findAllByIdCliente(cliente));
    }
}
