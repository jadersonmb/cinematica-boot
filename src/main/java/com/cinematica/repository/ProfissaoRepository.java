package com.cinematica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinematica.model.Profissao;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Long>{

}
