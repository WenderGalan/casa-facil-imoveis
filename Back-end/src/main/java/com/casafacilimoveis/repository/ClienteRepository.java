package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: ClienteRepository.java
 * Criado por : Wender Galan
 * Data da criação : 10/11/2018
 * Observação :
 * *********************************************
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    /**
     * Find by email usuario.
     *
     * @param email the email
     * @return the usuario
     */
    @Query("SELECT c FROM Cliente c WHERE c.email = ?1")
    public Cliente findByEmail(String email);
}
