package com.cinematica.interfaces;

import com.cinematica.dto.HorarioDTO;
import com.cinematica.model.Horario;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * HorarioMapper
 */
@Mapper(componentModel="spring")
public interface HorarioMapper {

    HorarioMapper INSTANCE = Mappers.getMapper( HorarioMapper.class );
 
    HorarioDTO toHorarioDTO(Horario horario); 
    
}