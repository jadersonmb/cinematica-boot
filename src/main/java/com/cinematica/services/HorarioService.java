package com.cinematica.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinematica.dto.HorarioDTO;
import com.cinematica.exception.HorarioException;

@Service
public interface HorarioService {

	public HorarioDTO buscarPorId(Integer id) throws HorarioException;

	public HorarioDTO salvar(HorarioDTO entidadeDTO) throws HorarioException;

	public void delete(HorarioDTO entidadeDTO) throws HorarioException;

	public List<HorarioDTO> listarTodos() throws HorarioException;
}
