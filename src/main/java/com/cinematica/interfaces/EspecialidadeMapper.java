package com.cinematica.interfaces;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.model.Especialidade;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Especialidade
 */
@Mapper(componentModel = "spring")
public interface EspecialidadeMapper {

    EspecialidadeMapper INSTANCE = Mappers.getMapper( EspecialidadeMapper.class );
 
    EspecialidadeDTO toEspecialidadeDTO(Especialidade especialidade); 
}