package com.cinematica.interfaces.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.model.Especialidade;

/**
 * Especialidade
 */
@Mapper(componentModel = "spring")
public interface EspecialidadeMapper {
	
	EspecialidadeMapper INSTANCE = Mappers.getMapper( EspecialidadeMapper.class );

    EspecialidadeDTO toEspecialidadeDTO(Especialidade especialidade); 
    
    Especialidade toEspecialidade(EspecialidadeDTO especialidadeDTO);
}