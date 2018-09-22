package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Anuncio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

    @Query("SELECT a FROM Anuncio a WHERE a.endereco.endereco LIKE %?1% OR a.endereco.bairro LIKE %?2% OR a.endereco.cidade LIKE %?3%")
    public Page<Anuncio> findByRuaBairroCidade(String rua, String bairro, String cidade, Pageable pageable);

    @Query("SELECT a FROM Anuncio a WHERE a.endereco.endereco LIKE %?1% OR a.endereco.bairro LIKE %?2%")
    public Page<Anuncio> findByRuaBairro(String rua, String bairro, Pageable pageable);

    @Query("SELECT a FROM Anuncio a WHERE a.endereco.endereco LIKE %?1% OR a.endereco.cidade LIKE %?2%")
    public Page<Anuncio> findByRuaCidade(String rua, String cidade, Pageable pageable);

    @Query("SELECT a FROM Anuncio a WHERE a.endereco.bairro LIKE %?1% OR a.endereco.cidade LIKE %?2%")
    public Page<Anuncio> findByBairroCidade(String bairro, String cidade, Pageable pageable);

    @Query("SELECT a FROM Anuncio a WHERE a.endereco.endereco LIKE %?1%")
    public Page<Anuncio> findByRua(String rua, Pageable pageable);

    @Query("SELECT a FROM Anuncio a WHERE a.endereco.bairro LIKE %?1%")
    public Page<Anuncio> findByBairro(String bairro, Pageable pageable);

    @Query("SELECT a FROM Anuncio a WHERE a.endereco.cidade LIKE %?1%")
    public Page<Anuncio> findByCidade(String cidade, Pageable pageable);

}
