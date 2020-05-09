package com.cinematica.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.HorarioDTO;
import com.cinematica.model.Horario;

/**
 * HorarioMapper
 */
@Mapper(componentModel="spring")
public interface HorarioMapper {

    HorarioDTO toHorarioDTO(Horario entidade);
    
    Horario toHorario(HorarioDTO entidadeDTO); 
    
}