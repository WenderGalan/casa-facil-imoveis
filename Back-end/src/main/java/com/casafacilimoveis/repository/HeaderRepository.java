package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.interceptor.Header;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: HeaderRepository.java
 * Criado por : Wender Galan
 * Data da criação : 10/11/2018
 * Observação :
 * *********************************************
 */
@Repository
public interface HeaderRepository extends JpaRepository<Header, Integer> {
}
