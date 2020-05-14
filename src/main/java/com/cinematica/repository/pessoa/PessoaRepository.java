package com.cinematica.repository.pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinematica.model.FluxoCaixa;
import com.cinematica.model.Pessoa;

/**
 * PessoaRespository
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>, PessoaRepositoryCustom {
	
	@Query(" FROM FluxoCaixa f WHERE f.pessoa.id = :id")
	List<FluxoCaixa> consultarFluxoPorPacienteId(@Param("id") Integer idPessoa);
}