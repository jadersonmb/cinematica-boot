package com.cinematica.services.horario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cinematica.dto.HorarioDTO;
import com.cinematica.exception.HorarioException;

@Service
public interface HorarioService {

	public HorarioDTO buscarPorId(Integer id) throws HorarioException;

	public HorarioDTO salvar(HorarioDTO entidadeDTO) throws HorarioException;

	public void delete(HorarioDTO entidadeDTO) throws HorarioException;

	public List<HorarioDTO> listarTodos() throws HorarioException;

	public Page<HorarioDTO> search(String searchTerm, Integer page, Integer linePage, String orderBy, String direction) throws HorarioException;

	public Page<HorarioDTO> listarTodosPages(Integer page, Integer linePage, String orderBy, String direction) throws HorarioException;
	
	public void deleteList(List<Integer> ids) throws HorarioException;
}
