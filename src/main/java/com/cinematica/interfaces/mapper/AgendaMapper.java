package com.cinematica.interfaces.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.AgendaDTO;
import com.cinematica.model.Agenda;

/**
 * AgendaMapper
 */
@Mapper(componentModel="spring")
public interface AgendaMapper {

	AgendaDTO toAgendaDTO(Agenda agenda);
	
}