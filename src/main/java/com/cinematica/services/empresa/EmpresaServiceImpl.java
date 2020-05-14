package com.cinematica.services.empresa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.dto.EmpresaDTO;
import com.cinematica.exception.EmpresaException;
import com.cinematica.exception.EspecialidadeException;
import com.cinematica.mapper.EmpresaMapper;
import com.cinematica.model.Empresa;
import com.cinematica.repository.empresa.EmpresaRepository;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public class EmpresaServiceImpl implements EmpresaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private EmpresaMapper mapper;

	@Override
	public EmpresaDTO buscarPorId(Integer id) throws EmpresaException {
		Optional<Empresa> obj = empresaRepository.findById(id);
		EmpresaDTO empresaDTO = mapper
				.toEmpresaDTO(obj.orElseThrow(() -> new EmpresaException("Empresa não existe")));
		return empresaDTO;
	}
    
	@Override
	public EmpresaDTO buscarPorNome(String nome) throws EmpresaException {
		Optional<Empresa> obj = empresaRepository.consultarPorNome(nome);
		EmpresaDTO empresaDTO = mapper
				.toEmpresaDTO(obj.orElseThrow(() -> new EmpresaException("Empresa não existe")));
		return empresaDTO;
	}
	
	public EmpresaDTO salvar(Empresa entidade) throws EmpresaException {
		Empresa empresa = empresaRepository.save(entidade);
		return mapper.toEmpresaDTO(empresa);
	}

	public void delete(EmpresaDTO empresaDTO) throws EspecialidadeException {
		empresaRepository.delete(mapper.toEmpresa(empresaDTO));
	}

	public List<EmpresaDTO> listarTodos() throws EspecialidadeException {
		List<Empresa> listaEmpresas = empresaRepository.findAll();
		List<EmpresaDTO> listaEmpresasDTO = new ArrayList<>();
		listaEmpresas.forEach(p-> listaEmpresasDTO.add(mapper.toEmpresaDTO(p)));
		return  listaEmpresasDTO;
	}

}
