package com.cinematica.services.especialidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.exception.EspecialidadeException;
import com.cinematica.framework.util.Utils;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.mapper.EspecialidadeMapper;
import com.cinematica.model.Especialidade;
import com.cinematica.model.SimNao;
import com.cinematica.repository.agenda.AgendaRepository;
import com.cinematica.repository.especialidade.EspecialidadeRepository;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public class EspecialidadeServiceImpl implements EspecialidadeService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EspecialidadeRepository especialidadeRepository;
	@Autowired
	private AgendaRepository agendaRepository;
	@Autowired
	private EspecialidadeMapper mapper;

	@Override
	public EspecialidadeDTO buscarPorId(Integer id) throws EspecialidadeException {
		Optional<Especialidade> obj = especialidadeRepository.findById(id);
		EspecialidadeDTO especialidadeDTO = mapper
				.toEspecialidadeDTO(obj.orElseThrow(() -> new EspecialidadeException("Erro especialidade não existe")));
		return especialidadeDTO;
	}

	@Override
	public EspecialidadeDTO salvar(EspecialidadeDTO entidadeDTO) throws EspecialidadeException {
		regrasNegocioSalvar(entidadeDTO);
		Especialidade entidade = mapper.toEspecialidade(entidadeDTO);
		Especialidade especialidade = especialidadeRepository.save(entidade);
		return mapper.toEspecialidadeDTO(especialidade);
	}

	@Override
	public void delete(EspecialidadeDTO entidade) throws EspecialidadeException {
		regrasNegocioExcluir(entidade);
		especialidadeRepository.delete(mapper.toEspecialidade(entidade));
	}

	@Override
	public Page<EspecialidadeDTO> listarTodosPages(Integer page, Integer linePage, String orderBy, String direction) throws EspecialidadeException {
		Page<Especialidade> listaEspecialidades = especialidadeRepository.findAll(PageRequest.of(page, linePage, Direction.valueOf(direction), orderBy));
		Page<EspecialidadeDTO> listaEspecialidadesDTO = listaEspecialidades.map(obj -> mapper.toEspecialidadeDTO(obj));
		return listaEspecialidadesDTO;
	}
	
	@Override
	public Page<EspecialidadeDTO> search(String searchTerm, Integer page, Integer linePage, String orderBy, String direction) throws EspecialidadeException {
		PageRequest pageRequest = PageRequest.of(page, linePage, Direction.valueOf(direction), orderBy);
		Page<Especialidade> listaEspecialidades = especialidadeRepository.search(searchTerm.toLowerCase(), pageRequest);
		Page<EspecialidadeDTO> listaEspecialidadesDTO = listaEspecialidades.map(obj -> mapper.toEspecialidadeDTO(obj));
		return listaEspecialidadesDTO;
	}
	
	@Override
	public List<EspecialidadeDTO> listarTodos() throws EspecialidadeException {
		List<Especialidade> listaEspecialidades = especialidadeRepository.findAll();
		List<EspecialidadeDTO> listaEspecialidadesDTO = new ArrayList<>();
		listaEspecialidades.forEach(p -> {
			if(p.getAtivo().equals(SimNao.Sim)) {
				listaEspecialidadesDTO.add(mapper.toEspecialidadeDTO(p));	
			}
		});
		return listaEspecialidadesDTO;
	}

	private void regrasNegocioExcluir(EspecialidadeDTO entidade) throws EspecialidadeException {
		verificaAgendaSeExisteEspecialidade(entidade);
	}

	private void regrasNegocioSalvar(EspecialidadeDTO entidade) throws EspecialidadeException {
		verificarCamposObrigatorios(entidade);
	}

	private void verificaAgendaSeExisteEspecialidade(EspecialidadeDTO entidade) throws EspecialidadeException {
		Integer numbers = this.agendaRepository.consultarSeExisteEspecialidade(entidade.getId());
		if (numbers > 0) {
			throw new EspecialidadeException("existem_consultas_que_utilizam_esta_especialidade");
		}
	}
	
	@Override
	public void deleteList(List<Integer> ids) throws EspecialidadeException {
		ids.forEach(obj -> delete(new EspecialidadeDTO(obj)));
	}

	public void verificarCamposObrigatorios(EspecialidadeDTO entidade) throws EspecialidadeException {
		if (VerificadorUtil.estaNuloOuVazio(entidade.getDescricao())) {
			throw new EspecialidadeException(Utils
					.verificarSeCampoEstaNulo(entidade.getDescricao(), "erro_descricao_nao_pode_ser_nula").toString());
		}
	}

}
