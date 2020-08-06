package com.cinematica.repository.especialidade;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cinematica.model.Especialidade;

@Repository
public interface EspecialidadeRepository extends JpaSpecificationExecutor<Especialidade>, JpaRepository<Especialidade, Integer>{
	
	@Query("FROM Especialidade e " + "WHERE LOWER(e.descricao) like %:searchTerm% ")
	Page<Especialidade> search(@Param("searchTerm") String searchTerm, Pageable pageable);

}
