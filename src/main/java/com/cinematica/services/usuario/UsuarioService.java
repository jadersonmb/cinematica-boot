package com.cinematica.services.usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinematica.dto.UsuarioDTO;
import com.cinematica.exception.UsuarioException;
import com.cinematica.model.Usuario;
/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public interface UsuarioService {
	
	public Usuario consultarPorId(Integer id) throws UsuarioException;

    public UsuarioDTO salvar(Usuario entidade) throws UsuarioException;

    public void delete(Usuario entidade) throws UsuarioException;

	public List<Usuario> listarTodos();
}
