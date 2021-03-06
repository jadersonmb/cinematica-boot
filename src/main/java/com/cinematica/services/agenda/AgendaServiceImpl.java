package com.cinematica.services.agenda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinematica.dto.AgendaDTO;
import com.cinematica.exception.AgendaException;
import com.cinematica.mapper.AgendaMapper;
import com.cinematica.model.Agenda;
import com.cinematica.repository.agenda.AgendaFilterDTO;
import com.cinematica.repository.agenda.AgendaRepository;
import com.cinematica.repository.agenda.AgendaSpec;

/**
 * AgendaService
 */
@Service
public class AgendaServiceImpl implements AgendaService, Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private AgendaRepository agendaRepository;
    @Autowired
    private AgendaMapper mapper;

    public Agenda buscarAgendaPorIdPaciente(Long id) throws AgendaException {
        Optional<Agenda> obj =  agendaRepository.consultarAgendaPorIdPaciente(id);
        return obj.orElseThrow(() -> new AgendaException("Agenda não existe para esse paciente"));
    }

    public Agenda buscarPorId(Long id) throws AgendaException {
        Optional<Agenda> obj =  agendaRepository.findById(id);
        return obj.orElseThrow(() -> new AgendaException("Agenda não existe"));
    }

    public List<Agenda> buscarAgendaPorFuncionarioId(Integer id) throws AgendaException{
        List<Agenda> obj = agendaRepository.consultarAgendaPorFuncionarioId(id);
        return obj;
    }

	public List<AgendaDTO> buscarAgendaDaSemanaPorPaciente(Integer id) throws AgendaException {
        List<Agenda> obj = agendaRepository.consultarAgendaDaSemanaPorPaciente(id);
        List<AgendaDTO> listObj = new ArrayList<>();
        obj.forEach(p-> listObj.add(mapper.toAgendaDTO(p)));
		return listObj;
	}

	@Override
	public Page<AgendaDTO> listarTodos(Pageable pageable, AgendaFilterDTO filter) throws AgendaException {
		return agendaRepository.findAll(AgendaSpec.searchDesc(filter), pageable)
				.map(mapper::toAgendaDTO);
	}
}