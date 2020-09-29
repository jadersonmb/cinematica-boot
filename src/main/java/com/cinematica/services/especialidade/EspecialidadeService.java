package com.cinematica.services.especialidade;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinematica.dto.EspecialidadeDTO;
import com.cinematica.exception.EspecialidadeException;
import com.cinematica.repository.especialidade.EspecialidadeFilterDTO;

/**
 * @author Jaderson Morais
 *
 */
@Service
public interface EspecialidadeService {

	public EspecialidadeDTO buscarPorId(Integer id) throws EspecialidadeException;

	public EspecialidadeDTO salvar(EspecialidadeDTO entidadeDTO) throws EspecialidadeException;

	public void delete(EspecialidadeDTO entidadeDTO) throws EspecialidadeException;

	public Page<EspecialidadeDTO> listarTodos(Pageable pageable, EspecialidadeFilterDTO filter) throws EspecialidadeException;
	
	public void deleteList(List<Integer> ids) throws EspecialidadeException;
}

