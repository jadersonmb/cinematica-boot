package com.cinematica.services.pessoa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.PessoaException;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public interface PessoaService {
	
	public Page<PessoaDTO> listarTodos(Pageable pageable, PessoaFilterDTO filter) throws PessoaException;
	
	public PessoaDTO buscarPorId(Integer id) throws PessoaException;

	public PessoaDTO salvar(PessoaDTO entidade) throws PessoaException;
	
	public void delete(PessoaDTO entidadeDTO) throws PessoaException; 
}
