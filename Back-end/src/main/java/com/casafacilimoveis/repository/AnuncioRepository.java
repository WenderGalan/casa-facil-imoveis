package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Anuncio repository.
 */
@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

    /**
     * Find by nome anuncio.
     *
     * @param nome the nome
     * @return the anuncio
     */
    @Query("SELECT a FROM Anuncio a WHERE a.titulo LIKE %" + "?1" + "%")
    public Anuncio findByNome(String nome);

    /**
     * Find one by id anuncio.
     *
     * @param id the id
     * @return the anuncio
     */
    @Query("SELECT a FROM Anuncio a WHERE a.id = ?1 ")
    public Anuncio findOneById(Integer id);
}
