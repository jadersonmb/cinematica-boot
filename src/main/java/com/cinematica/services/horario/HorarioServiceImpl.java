package com.cinematica.services.horario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cinematica.dto.HorarioDTO;
import com.cinematica.exception.HorarioException;
import com.cinematica.framework.util.Utils;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.mapper.HorarioMapper;
import com.cinematica.model.Horario;
import com.cinematica.repository.agenda.AgendaRepository;
import com.cinematica.repository.horario.HorarioRepository;

@Service
public class HorarioServiceImpl implements HorarioService, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    private HorarioRepository horarioRepository;
	@Autowired
	private AgendaRepository agendaRepository;
	@Autowired
	private HorarioMapper mapper;

	public HorarioDTO buscarPorId(Integer id) throws HorarioException {
		Optional<Horario> obj = horarioRepository.findById(id);
		HorarioDTO horarioDTO = mapper
				.toHorarioDTO(obj.orElseThrow(() -> new HorarioException()));
		return horarioDTO;
	}

	@Override
	public HorarioDTO salvar(HorarioDTO entidadeDTO) throws HorarioException {
		regrasNegocioSalvar(entidadeDTO);
		
		Horario entidade = mapper.toHorario(entidadeDTO);
		Horario horario = horarioRepository.save(entidade);
		return mapper.toHorarioDTO(horario);
	}

	@Override
	public void delete(HorarioDTO entidadeDTO) throws HorarioException {
		regrasNegocioExcluir(entidadeDTO);
		horarioRepository.delete(mapper.toHorario(entidadeDTO));
	}

	@Override
	public List<HorarioDTO> listarTodos() throws HorarioException {
		List<Horario> listaHorarios = horarioRepository.findAll();
		List<HorarioDTO> listaHorariosDTO = new ArrayList<>();
		listaHorarios.forEach(p-> listaHorariosDTO.add(mapper.toHorarioDTO(p)));
		return  listaHorariosDTO;
	}
	
	private void regrasNegocioExcluir(HorarioDTO entidade) throws HorarioException {
		verificaSeExisteHorarioRelacionado(entidade);
		verificaSeExisteHorarioNaAgenda(entidade);
	}

	private void verificaSeExisteHorarioNaAgenda(HorarioDTO entidade) throws HorarioException {
		Integer numbers = agendaRepository.consultarSeExisteHorario(entidade.getId());
		if (numbers > 0) {
			throw new HorarioException("impossivel_excluir_usuario_existem_consultas_marcadas");
		}
	}

	private void verificaSeExisteHorarioRelacionado(HorarioDTO entidade) throws HorarioException {
		/* Regra de negocio a implementar da configuração do sistema */
	}

	private void regrasNegocioSalvar(HorarioDTO entidade) throws HorarioException {
		verificarCamposObrigatorios(entidade);
	}
	
	@Override
	public void deleteList(List<Integer> ids) throws HorarioException {
		ids.forEach(obj -> delete(new HorarioDTO(obj)));
	}

	@Override
	public Page<HorarioDTO> search(String searchTerm, Integer page, Integer linePage, String orderBy, String direction)
			throws HorarioException {
		PageRequest pageRequest = PageRequest.of(page, linePage, Direction.valueOf(direction), orderBy);
		Page<Horario> listaHorarios = horarioRepository.search(searchTerm.toLowerCase(), pageRequest);
		Page<HorarioDTO> listaHorariosDTO = listaHorarios.map(obj -> mapper.toHorarioDTO(obj));
		return listaHorariosDTO;
	}

	@Override
	public Page<HorarioDTO> listarTodosPages(Integer page, Integer linePage, String orderBy, String direction)
			throws HorarioException {
		Page<Horario> listaHorarios = horarioRepository.findAll(PageRequest.of(page, linePage, Direction.valueOf(direction), orderBy));
		Page<HorarioDTO> listaHorariosDTO = listaHorarios.map(obj -> mapper.toHorarioDTO(obj));
		return listaHorariosDTO;
	}
	
	public void verificarCamposObrigatorios(HorarioDTO entidade) throws HorarioException {
		if (VerificadorUtil.estaNuloOuVazio(entidade.getHorarioInicio())
				|| VerificadorUtil.estaNuloOuVazio(entidade.getHorarioFim())) {
			throw new HorarioException(Utils
					.verificarSeCampoEstaNulo(entidade.getHorarioInicio(), "erro_horario_nao_pode_ser_nula").toString());
		}
	}

}

