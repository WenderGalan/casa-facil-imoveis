package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.enums.TipoNegocio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: AnuncioRepository.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
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
    public Page<Anuncio> buscarTodosPorIdAnunciante(Integer id, Pageable pageable);

    @Query(" SELECT a FROM Anuncio a" +
            " WHERE (?1 IS NULL OR (?1 IS NOT NULL AND LOWER(a.endereco.endereco) LIKE '%'||LOWER(?1)||'%'))" +
            " OR (?1 IS NULL OR (?1 IS NOT NULL AND LOWER(a.endereco.bairro) LIKE '%'||LOWER(?1)||'%'))" +
            " OR (?1 IS NULL OR (?1 IS NOT NULL AND LOWER(a.endereco.cidade) LIKE '%'||LOWER(?1)||'%'))")
    public Page<Anuncio> findAnunciosByParams(@Nullable String pesquisa, Pageable pageable);

    @Query("SELECT a FROM Anuncio a JOIN FETCH a.endereco e " +
            "WHERE a.anunciante.id = ?1 AND a.tipoNegocio = ?2 " +
            "ORDER BY e.estado, e.cidade, e.bairro, e.endereco, e.numero")
    public List<Anuncio> findAllAnunciosByUserAndTipoNegocio(Integer idUsuario, TipoNegocio tipoNegocio);

}
