package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: FavoritoRepository.java
 * Criado por : Wender Galan
 * Data da criação : 11/11/2018
 * Observação :
 * *********************************************
 */
@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {

    @Query("select f from Favorito f where f.cliente.id = ?1")
    List<Favorito> findAllByIdCliente(Integer cliente);

    @Query("select f from Favorito f where f.cliente.id = ?1 and f.anuncio.id = ?2")
    Favorito findByClienteAndAnuncio(Integer cliente, Integer anuncio);
}
