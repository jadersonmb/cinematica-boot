package com.cinematica.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.exception.UsuarioException;
import com.cinematica.interfaces.services.UsuarioService;
import com.cinematica.model.Usuario;
import com.cinematica.repository.UsuarioRepository;

/**
 * UsuarioService
 */
@Service
public class UsuarioServiceImpl implements UsuarioService, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario consultarPorId(Integer id) throws UsuarioException {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        return obj.orElseThrow(() -> new UsuarioException("Pessoa não Encontrada"));
    }

    public Usuario salvar(Usuario entidade) throws UsuarioException {
        return usuarioRepository.save(entidade);
    }

    public void delete(Usuario entidade) throws UsuarioException {
        usuarioRepository.delete(entidade);
    }

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
}