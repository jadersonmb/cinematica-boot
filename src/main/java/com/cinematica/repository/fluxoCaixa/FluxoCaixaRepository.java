package com.cinematica.repository.fluxoCaixa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinematica.model.FluxoCaixa;

@Repository
public interface FluxoCaixaRepository extends JpaRepository<FluxoCaixa, Long>, FluxoCaiaxRepositoryCustom {

	@Query("FROM FluxoCaixa f WHERE f.formaPagamento.id = :id")
	Long verificaAgendaSeExisteFluxoPorPagamento (@Param("id") Long idPagamento); 
	
	@Query(" FROM FluxoCaixa f WHERE f.pessoa.id = :id")
	List<FluxoCaixa> consultarFluxoPorPacienteId(@Param("id") Integer idPessoa);

}
