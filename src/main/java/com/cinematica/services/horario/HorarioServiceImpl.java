package com.cinematica.services.horario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.dto.HorarioDTO;
import com.cinematica.exception.HorarioException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.mapper.HorarioMapper;
import com.cinematica.model.Horario;
import com.cinematica.repository.horario.HorarioRepository;

@Service
public class HorarioServiceImpl implements HorarioService,Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
    private HorarioRepository horarioRepository;
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
		
		if(VerificadorUtil.estaNulo(entidadeDTO.getHorarioInicio()) || VerificadorUtil.estaNulo(entidadeDTO.getHorarioFim())) {
			throw new HorarioException("erro_horario_nao_pode_ser_nula");
		}
		
		Horario entidade = mapper.toHorario(entidadeDTO);
		Horario horario = horarioRepository.save(entidade);
		return mapper.toHorarioDTO(horario);
	}

	@Override
	public void delete(HorarioDTO entidadeDTO) throws HorarioException {
		horarioRepository.delete(mapper.toHorario(entidadeDTO));
	}

	@Override
	public List<HorarioDTO> listarTodos() throws HorarioException {
		List<Horario> listaHorarios = horarioRepository.findAll();
		List<HorarioDTO> listaHorariosDTO = new ArrayList<>();
		listaHorarios.forEach(p-> listaHorariosDTO.add(mapper.toHorarioDTO(p)));
		return  listaHorariosDTO;
	}
}

