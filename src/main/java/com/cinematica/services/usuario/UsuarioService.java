package com.cinematica.services.usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinematica.dto.UsuarioDTO;
import com.cinematica.exception.UsuarioException;
/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public interface UsuarioService {
	
	public UsuarioDTO consultarPorId(Integer id) throws UsuarioException;

    public UsuarioDTO salvar(UsuarioDTO entidade) throws UsuarioException;

    public void delete(UsuarioDTO entidade) throws UsuarioException;

	public List<UsuarioDTO> listarTodos() throws UsuarioException;
}
