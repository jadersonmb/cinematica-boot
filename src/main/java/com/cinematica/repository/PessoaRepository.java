package com.cinematica.repository;

import com.cinematica.model.Pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * PessoaRespository
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    
}