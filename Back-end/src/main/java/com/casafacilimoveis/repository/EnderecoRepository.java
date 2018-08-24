package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: EnderecoRepository.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
