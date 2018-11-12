package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.interceptor.Crashlytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * casafacilimoveis
 * Wender Galan
 * Todos os direitos reservados ©
 * *********************************************
 * Nome do arquivo: CrashlyticsRepository.java
 * Criado por : Wender Galan
 * Data da criação : 10/11/2018
 * Observação :
 * *********************************************
 */
@Repository
public interface CrashlyticsRepository extends JpaRepository<Crashlytics, Integer> {
}
