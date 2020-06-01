package com.cinematica.repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinematica.model.Pessoa;

/**
 * PessoaRespository
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, PessoaRepositoryCustom {
	
	@Query(" SELECT count(id) FROM Pessoa f WHERE f.profissao.id = :id")
	Integer consultarSeExistePessoaVinculada(Long id);
}