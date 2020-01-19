/**
 * 
 */
package com.cinematica.interfaces.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.exception.EspecialidadeException;
import com.cinematica.model.Especialidade;

/**
 * @author Jaderson Morais
 *
 */
@Service
public interface EspecialidadeService {

	public EspecialidadeDTO buscarPorId(Integer id) throws EspecialidadeException;

	public EspecialidadeDTO salvar(Especialidade entidade) throws EspecialidadeException;

	public void delete(EspecialidadeDTO entidadeDTO) throws EspecialidadeException;

	public List<EspecialidadeDTO> listarTodos() throws EspecialidadeException;
}