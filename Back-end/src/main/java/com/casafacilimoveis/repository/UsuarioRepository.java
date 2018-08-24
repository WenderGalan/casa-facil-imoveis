package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * casa-facil-imoveis
 * Wender Galan
 * Todos os direitos reservados ©
 **********************************************
 * Nome do arquivo: UsuarioRepository.java
 * Criado por : Wender Galan
 * Data da criação :
 * Observação :
 **********************************************
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Find one by id usuario.
     *
     * @param id the id
     * @return the usuario
     */
    @Query("SELECT u FROM Usuario u WHERE u.id = ?1 ")
    public Usuario findOneById(Integer id);

    /**
     * Find by email usuario.
     *
     * @param email the email
     * @return the usuario
     */
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    public Usuario findByEmail(String email);

    /**
     * Find by nome usuario.
     *
     * @param nome the nome
     * @return the usuario
     */
    @Query("SELECT u FROM Usuario u WHERE u.nome LIKE %"+ "?1" +"%")
    public Usuario findByNome(String nome);
}
