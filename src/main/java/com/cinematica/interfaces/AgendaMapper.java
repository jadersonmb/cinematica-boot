package com.cinematica.interfaces;

import org.mapstruct.Mapper;

import com.cinematica.dto.AgendaDTO;
import com.cinematica.model.Agenda;

/**
 * AgendaMapper
 */
@Mapper(componentModel="spring")
public interface AgendaMapper {

	AgendaDTO toAgendaDTO(Agenda entidade);
	
	Agenda toAgenda(AgendaDTO entidadeDTO);
	
}