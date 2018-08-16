package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.id = ?1 ")
    public Usuario findOneById(Integer id);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    public Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE %"+ "?1" +"%")
    public Usuario findByNome(String nome);
}
