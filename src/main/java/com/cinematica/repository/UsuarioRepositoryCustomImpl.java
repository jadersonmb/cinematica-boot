package com.cinematica.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cinematica.model.Usuario;

/**
 * AgendaRepositoryCustomImpl
 */
@Repository
@Transactional(readOnly = true)
public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public Usuario consultarUsuarioPorPessoa(Integer idPessoa) {
		return this.entityManager.createQuery(" SELECT u FROM Usuario u WHERE u.pessoa.id = :idPessoa ", Usuario.class)
				.setParameter("idPessoa", idPessoa)
				.getSingleResult();
	}
}