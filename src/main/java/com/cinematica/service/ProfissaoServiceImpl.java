package com.cinematica.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.dto.ProfissaoDTO;
import com.cinematica.exception.EspecialidadeException;
import com.cinematica.exception.ProfissaoException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.interfaces.ProfissaoMapper;
import com.cinematica.interfaces.services.ProfissaoService;
import com.cinematica.model.Profissao;
import com.cinematica.repository.ProfissaoRepository;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public class ProfissaoServiceImpl implements ProfissaoService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProfissaoRepository profissaoRepository;
	@Autowired
	private ProfissaoMapper mapper;

	public ProfissaoDTO buscarPorId(Long id) throws ProfissaoException {
		Optional<Profissao> obj = profissaoRepository.findById(id);
		ProfissaoDTO profissaoDTO = mapper
				.toProfissaoDTO(obj.orElseThrow(() -> new EspecialidadeException()));
		return profissaoDTO;
	}

	public ProfissaoDTO salvar(ProfissaoDTO entidadeDTO) throws ProfissaoException {
		
		if(VerificadorUtil.estaNulo(entidadeDTO.getDescricao())) {
			throw new EspecialidadeException("erro_descricao_nao_pode_ser_nula");
		}
		
		Profissao entidade = mapper.toProfissao(entidadeDTO);
		Profissao profissao = profissaoRepository.save(entidade);
		return mapper.toProfissaoDTO(profissao);
	}

	public void delete(ProfissaoDTO entidadeDTO) throws ProfissaoException {
		profissaoRepository.delete(mapper.toProfissao(entidadeDTO));
	}

	public List<ProfissaoDTO> listarTodos() throws ProfissaoException {
		List<Profissao> listaProfissoes = profissaoRepository.findAll();
		List<ProfissaoDTO> listaProfissoesDTO = new ArrayList<>();
		listaProfissoes.forEach(p-> listaProfissoesDTO.add(mapper.toProfissaoDTO(p)));
		return  listaProfissoesDTO;
	}

}
