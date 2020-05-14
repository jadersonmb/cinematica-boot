package com.cinematica.repository.pessoa;

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
public class PessoaRepositoryCustomImpl implements PessoaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

}