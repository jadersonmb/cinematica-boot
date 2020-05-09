package com.cinematica.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.UsuarioDTO;
import com.cinematica.model.Usuario;

@Mapper(componentModel="spring")
public interface UsuarioMapper {
	
	UsuarioDTO toUsuarioDTO(Usuario entidade);
	
	Usuario toUsuario(UsuarioDTO entidadeDTO);

}
