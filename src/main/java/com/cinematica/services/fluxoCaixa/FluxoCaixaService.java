package com.cinematica.services.fluxoCaixa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinematica.dto.FluxoCaixaDTO;
import com.cinematica.exception.FluxoCaixaException;
import com.cinematica.repository.fluxoCaixa.FluxoCaixaFilterDTO;

/**
 * @author Jaderson Morais
 *
 */
@Service
public interface FluxoCaixaService {

	public FluxoCaixaDTO buscarPorId(Long id) throws FluxoCaixaException;

	public FluxoCaixaDTO salvar(FluxoCaixaDTO entidadeDTO) throws FluxoCaixaException;

	public void delete(FluxoCaixaDTO entidadeDTO) throws FluxoCaixaException;

	public Page<FluxoCaixaDTO> listarTodosPages(Pageable pageable, FluxoCaixaFilterDTO filter) throws FluxoCaixaException;

	public void deleteList(List<Long> ids) throws FluxoCaixaException;

}
