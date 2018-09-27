package com.casafacilimoveis.repository;

import com.casafacilimoveis.model.entities.Anuncio;
import com.casafacilimoveis.model.entities.Endereco;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import javax.validation.constraints.Null;
import java.util.List;

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

    @Query("SELECT DISTINCT UPPER(e.cidade||' - '||e.estado) FROM Endereco e WHERE ?1 is null or (?1 is not null and lower(e.cidade) like '%'||LOWER(?1)||'%')")
    public List<String> findCidadesAutoComplete(@Nullable String text, Pageable pageable);

    @Query("SELECT DISTINCT UPPER(e.bairro||' - '||e.cidade||' - '||e.estado) FROM Endereco e WHERE ?1 is null or (?1 is not null and lower(e.bairro) like '%'||LOWER(?1)||'%')")
    public List<String> findBairroAutoComplete(@Nullable String text, Pageable pageable);

    @Query("SELECT DISTINCT UPPER(e.endereco||' - '||e.bairro||' - '||e.cidade) FROM Endereco e WHERE ?1 is null or (?1 is not null and lower(e.endereco) like '%'||LOWER(?1)||'%')")
    public List<String> findEnderecoAutoComplete(@Nullable String text, Pageable pageable);

    //Limita as consultas para apenas 3 resultados
    default List<String> findCidadesAutoComplete(@Nullable String text){
        return findCidadesAutoComplete(text, PageRequest.of(0,3));
    }

    default List<String> findBairroAutoComplete(@Nullable String text){
        return findBairroAutoComplete(text, PageRequest.of(0,3));
    }

    default List<String> findEnderecoAutoComplete(@Nullable String text){
        return findEnderecoAutoComplete(text, PageRequest.of(0,3));
    }
}
