package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: ImagemRepository.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 * *********************************************
 */
@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {

    @Query("SELECT i FROM Imagem i WHERE i.id = ?1 ")
    public Imagem findOneById(String id);

    @Query("SELECT i FROM Imagem i WHERE i.anuncio.id = ?1")
    public List<Imagem> findAllByIdAnuncio(Integer id);
}
