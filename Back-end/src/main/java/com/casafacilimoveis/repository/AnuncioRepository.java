package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: AnuncioRepository.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
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

    @Query("SELECT a FROM Anuncio a WHERE a.anunciante.id = ?1")
    public List<Anuncio> buscarTodosPorIdAnunciante(Integer id);
}
