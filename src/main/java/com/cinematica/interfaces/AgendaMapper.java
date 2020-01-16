package com.cinematica.interfaces;

import com.cinematica.dto.AgendaDTO;
import com.cinematica.model.Agenda;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * AgendaMapper
 */
@Mapper(componentModel="spring")
public interface AgendaMapper {

    AgendaMapper INSTANCE = Mappers.getMapper( AgendaMapper.class );
 
    AgendaDTO toAgendaDTO(Agenda agenda); 
}