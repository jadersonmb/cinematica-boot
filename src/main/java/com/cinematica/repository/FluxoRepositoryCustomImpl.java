package com.cinematica.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * AgendaRepositoryCustomImpl
 */
@Repository
@Transactional(readOnly = true)
public class FluxoRepositoryCustomImpl implements FluxoCaiaxRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
}