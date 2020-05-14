package com.cinematica.services.profissao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinematica.dto.ProfissaoDTO;
import com.cinematica.exception.ProfissaoException;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public interface ProfissaoService {
	
	public ProfissaoDTO buscarPorId(Long id) throws ProfissaoException;

	public ProfissaoDTO salvar(ProfissaoDTO entidadeDTO) throws ProfissaoException;

	public void delete(ProfissaoDTO entidadeDTO) throws ProfissaoException;

	public List<ProfissaoDTO> listarTodos() throws ProfissaoException;
}
