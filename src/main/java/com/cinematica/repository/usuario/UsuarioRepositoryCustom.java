package com.cinematica.repository.usuario;

import org.springframework.stereotype.Repository;

import com.cinematica.model.Usuario;

/**
 * AgendaRepositoryCustom
 */
@Repository
public interface UsuarioRepositoryCustom {

	Usuario consultarUsuarioPorPessoa(Integer id);
}