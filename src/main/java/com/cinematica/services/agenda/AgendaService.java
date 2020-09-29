package com.cinematica.services.agenda;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinematica.dto.AgendaDTO;
import com.cinematica.exception.AgendaException;
import com.cinematica.model.Agenda;
import com.cinematica.repository.agenda.AgendaFilterDTO;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public interface AgendaService {
	
    public Agenda buscarAgendaPorIdPaciente(Long id) throws AgendaException;

    public Agenda buscarPorId(Long id) throws AgendaException;

    public List<Agenda> buscarAgendaPorFuncionarioId(Integer id) throws AgendaException;

	public List<AgendaDTO> buscarAgendaDaSemanaPorPaciente(Integer id) throws AgendaException;

	public Page<AgendaDTO> listarTodos(Pageable pageable, AgendaFilterDTO filter) throws AgendaException;
}
