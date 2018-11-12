package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Anunciante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: AnuncianteRepository.java
 * Criado por : Wender Galan
 * Data da criação : 10/11/2018
 * Observação :
 * *********************************************
 */
@Repository
public interface AnuncianteRepository extends JpaRepository<Anunciante, Integer> {

    @Query("SELECT u FROM Anunciante u WHERE u.id = ?1 ")
    public Anunciante findOneById(Integer id);

    @Query("SELECT u FROM Anunciante u WHERE u.email = ?1")
    public Anunciante findByEmail(String email);
}
