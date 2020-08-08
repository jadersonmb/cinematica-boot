package com.cinematica.services.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.dto.UsuarioDTO;
import com.cinematica.exception.UsuarioException;
import com.cinematica.mapper.UsuarioMapper;
import com.cinematica.model.Usuario;
import com.cinematica.repository.usuario.UsuarioRepository;

/**
 * UsuarioService
 */
@Service
public class UsuarioServiceImpl implements UsuarioService, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper mapper;
    

    public UsuarioDTO consultarPorId(Integer id) throws UsuarioException {
        Optional<Usuario> obj = usuarioRepository.findById(id);
        UsuarioDTO usuarioDTO = mapper
				.toUsuarioDTO(obj.orElseThrow(() -> new UsuarioException("Erro usuário não existe")));
        return usuarioDTO;
    }

    public UsuarioDTO salvar(UsuarioDTO entidade) throws UsuarioException {
        Usuario usuario = usuarioRepository.save(mapper.toUsuario(entidade));
        return mapper.toUsuarioDTO(usuario);
    }

    public void delete(UsuarioDTO entidade) throws UsuarioException {
        usuarioRepository.delete(mapper.toUsuario(entidade));
    }

	public List<UsuarioDTO> listarTodos() {
		List<Usuario> users = usuarioRepository.findAll();
		List<UsuarioDTO> usersDTO = new ArrayList<>();
		users.forEach(p-> usersDTO.add(mapper.toUsuarioDTO(p)));
		return usersDTO;
	}
}