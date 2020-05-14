package com.cinematica.services.empresa;

import org.springframework.stereotype.Service;

import com.cinematica.dto.EmpresaDTO;
import com.cinematica.exception.EmpresaException;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public interface EmpresaService {

	public EmpresaDTO buscarPorId(Integer id) throws EmpresaException;
	
	public EmpresaDTO buscarPorNome(String nome) throws EmpresaException;
}
