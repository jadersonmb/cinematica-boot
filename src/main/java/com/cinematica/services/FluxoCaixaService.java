package com.cinematica.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinematica.dto.FluxoCaixaDTO;
import com.cinematica.exception.FluxoCaixaException;

/**
 * @author Jaderson Morais
 *
 */
@Service
public interface FluxoCaixaService {

	public FluxoCaixaDTO buscarPorId(Long id) throws FluxoCaixaException;

	public FluxoCaixaDTO salvar(FluxoCaixaDTO entidadeDTO) throws FluxoCaixaException;

	public void delete(FluxoCaixaDTO entidadeDTO) throws FluxoCaixaException;

	public List<FluxoCaixaDTO> listarTodos() throws FluxoCaixaException;
}
