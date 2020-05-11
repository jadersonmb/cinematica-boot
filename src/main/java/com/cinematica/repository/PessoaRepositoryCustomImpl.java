package com.cinematica.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Repository
@Transactional(readOnly = true)
public class PessoaRepositoryCustomImpl implements PessoaCaiaxRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

}