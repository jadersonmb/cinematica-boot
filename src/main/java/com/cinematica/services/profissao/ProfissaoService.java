package com.cinematica.services.profissao;

import java.util.List;

import org.springframework.data.domain.Page;
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
	
	public Page<ProfissaoDTO> listarTodosPages(Integer page, Integer linePage, String orderBy, String direction) throws ProfissaoException;
	
	public Page<ProfissaoDTO> search(String searchTerm, Integer page, Integer linePage, String orderBy, String direction) throws ProfissaoException;
	
	public void deleteList(List<Long> ids) throws ProfissaoException;
}
