package com.cinematica.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.model.Especialidade;

/**
 * Especialidade
 */
@Mapper(componentModel = "spring")
public interface EspecialidadeMapper {
	
    EspecialidadeDTO toEspecialidadeDTO(Especialidade entidade); 
    
    Especialidade toEspecialidade(EspecialidadeDTO entidadeDTO);
}