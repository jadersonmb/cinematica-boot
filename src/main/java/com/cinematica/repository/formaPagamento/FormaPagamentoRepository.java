package com.cinematica.repository.formaPagamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinematica.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{

	@Query("FROM FormaPagamento f " + "WHERE LOWER(f.descricao) like %:searchTerm% ")
	Page<FormaPagamento> search(@Param("searchTerm") String searchTerm, Pageable pageable);

}
