package com.cinematica.repository.profissao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinematica.model.Profissao;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Long>{

	@Query("FROM Profissao p " + "WHERE LOWER(p.descricao) like %:searchTerm% ")
	Page<Profissao> search(@Param("searchTerm") String searchTerm, Pageable pageable);
}
