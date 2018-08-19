package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

    @Query("SELECT a FROM Anuncio a WHERE a.titulo LIKE %" + "?1" + "%")
    public Anuncio findByNome(String nome);

    @Query("SELECT a FROM Anuncio a WHERE a.id = ?1 ")
    public Anuncio findOneById(Integer id);
}
