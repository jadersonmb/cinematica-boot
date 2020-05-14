package com.cinematica.services.agenda;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinematica.dto.AgendaDTO;
import com.cinematica.exception.AgendaException;
import com.cinematica.model.Agenda;

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

	public List<AgendaDTO> listarTodos() throws AgendaException;
}
