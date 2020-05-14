package com.cinematica.services.pessoa;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.exception.PessoaException;
import com.cinematica.model.Pessoa;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public interface PessoaService {
	
	public List<Pessoa> listarTodos() throws PessoaException;

	public PessoaDTO buscarPorId(Integer id) throws PessoaException;

	public PessoaDTO salvar(PessoaDTO entidade) throws PessoaException;
	
	public void delete(PessoaDTO entidadeDTO) throws PessoaException; 
}
