package com.cinematica.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.exception.EspecialidadeException;
import com.cinematica.interfaces.EspecialidadeMapper;
import com.cinematica.interfaces.services.EspecialidadeService;
import com.cinematica.model.Especialidade;
import com.cinematica.repository.EspecialidadeRepository;

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
	private EspecialidadeMapper mapper;

	public EspecialidadeDTO buscarPorId(Integer id) throws EspecialidadeException {
		Optional<Especialidade> obj = especialidadeRepository.findById(id);
		EspecialidadeDTO especialidadeDTO = mapper
				.toEspecialidadeDTO(obj.orElseThrow(() -> new EspecialidadeException("Especialidade não existe")));
		return especialidadeDTO;
	}

	public EspecialidadeDTO salvar(Especialidade entidade) throws EspecialidadeException {
		Especialidade especialidade = especialidadeRepository.save(entidade);
		return mapper.toEspecialidadeDTO(especialidade);
	}

	public void delete(EspecialidadeDTO entidadeDTO) throws EspecialidadeException {
		especialidadeRepository.delete(mapper.toEspecialidade(entidadeDTO));
	}

	public List<EspecialidadeDTO> listarTodos() throws EspecialidadeException {
		List<Especialidade> listaEspecialidades = especialidadeRepository.findAll();
		List<EspecialidadeDTO> listaEspecialidadesDTO = new ArrayList<>();
		listaEspecialidades.forEach(p-> listaEspecialidadesDTO.add(mapper.toEspecialidadeDTO(p)));
		return  listaEspecialidadesDTO;
	}

}
