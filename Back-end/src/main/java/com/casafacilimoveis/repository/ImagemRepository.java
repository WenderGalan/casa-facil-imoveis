package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
}
