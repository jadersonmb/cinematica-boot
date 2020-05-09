package com.cinematica.services;

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

	public Pessoa buscarPorId(Integer id) throws PessoaException;

	public void salvarAll(List<Pessoa> listaPessoas) throws PessoaException;

	public PessoaDTO salvar(Pessoa entidade) throws PessoaException;
	
	public void delete(Pessoa entidade) throws PessoaException; 

	public PessoaDTO toDTO (Pessoa entidade) throws PessoaException;
}
