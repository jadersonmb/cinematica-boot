package com.cinematica.repository;

import com.cinematica.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UsuarioRepository
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, UsuarioRepositoryCustom {

    
}