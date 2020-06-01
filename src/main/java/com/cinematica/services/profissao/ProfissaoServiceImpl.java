package com.cinematica.services.profissao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cinematica.dto.ProfissaoDTO;
import com.cinematica.exception.EspecialidadeException;
import com.cinematica.exception.ProfissaoException;
import com.cinematica.framework.util.Utils;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.mapper.ProfissaoMapper;
import com.cinematica.model.Profissao;
import com.cinematica.repository.pessoa.PessoaRepository;
import com.cinematica.repository.profissao.ProfissaoRepository;

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
	private PessoaRepository pessoaRepository;
	@Autowired
	private ProfissaoMapper mapper;

	@Override
	public ProfissaoDTO buscarPorId(Long id) throws ProfissaoException {
		Optional<Profissao> obj = profissaoRepository.findById(id);
		ProfissaoDTO profissaoDTO = mapper
				.toProfissaoDTO(obj.orElseThrow(() -> new ProfissaoException()));
		return profissaoDTO;
	}
	
	@Override
	public ProfissaoDTO salvar(ProfissaoDTO entidadeDTO) throws ProfissaoException {
		regrasNegocioSalvar(entidadeDTO);
		if(VerificadorUtil.estaNulo(entidadeDTO.getDescricao())) {
			throw new EspecialidadeException("erro_descricao_nao_pode_ser_nula");
		}
		
		Profissao entidade = mapper.toProfissao(entidadeDTO);
		Profissao profissao = profissaoRepository.save(entidade);
		return mapper.toProfissaoDTO(profissao);
	}

	@Override
	public void delete(ProfissaoDTO entidadeDTO) throws ProfissaoException {
		regrasNegocioExcluir(entidadeDTO);
		profissaoRepository.delete(mapper.toProfissao(entidadeDTO));
	}
	
	private void regrasNegocioExcluir(ProfissaoDTO entidade) throws ProfissaoException {
		verificarSeExistePessoaComProfissao(entidade);
	}

	private void regrasNegocioSalvar(ProfissaoDTO entidade) throws ProfissaoException {
		verificarCamposObrigatorios(entidade);
	}
	
	private void verificarSeExistePessoaComProfissao(ProfissaoDTO entidade) throws ProfissaoException {
		Integer numbers = this.pessoaRepository.consultarSeExistePessoaVinculada(entidade.getId());
		if (VerificadorUtil.naoEstaNulo(numbers) && numbers > 0) {
			throw new ProfissaoException("impossivel_excluir_existe_paciente_vinculado");
		}
	}

	@Override
	public Page<ProfissaoDTO> search(String searchTerm, Integer page, Integer linePage, String orderBy, String direction) throws ProfissaoException {
		PageRequest pageRequest = PageRequest.of(page, linePage, Direction.valueOf(direction), orderBy);
		Page<Profissao> listaProfissoes = profissaoRepository.search(searchTerm.toLowerCase(), pageRequest);
		Page<ProfissaoDTO> listaProfissoesDTO = listaProfissoes.map(obj -> mapper.toProfissaoDTO(obj));
		return listaProfissoesDTO;
	}

	@Override
	public Page<ProfissaoDTO> listarTodosPages(Integer page, Integer linePage, String orderBy, String direction) throws ProfissaoException {
		Page<Profissao> listaProfissoes = profissaoRepository.findAll(PageRequest.of(page, linePage, Direction.valueOf(direction), orderBy));
		Page<ProfissaoDTO> listaProfissoesDTO = listaProfissoes.map(obj -> mapper.toProfissaoDTO(obj));
		return listaProfissoesDTO;
	}
	
	@Override
	public void deleteList(List<Long> ids) throws EspecialidadeException {
		ids.forEach(obj -> delete(new ProfissaoDTO(obj)));
	}

	public List<ProfissaoDTO> listarTodos() throws ProfissaoException {
		List<Profissao> listaProfissoes = profissaoRepository.findAll();
		List<ProfissaoDTO> listaProfissoesDTO = new ArrayList<>();
		listaProfissoes.forEach(p-> listaProfissoesDTO.add(mapper.toProfissaoDTO(p)));
		return  listaProfissoesDTO;
	}
	
	public void verificarCamposObrigatorios(ProfissaoDTO entidade) {
		if (VerificadorUtil.estaNuloOuVazio(entidade.getDescricao())) {
			throw new ProfissaoException(Utils
					.verificarSeCampoEstaNulo(entidade.getDescricao(), "erro_descricao_nao_pode_ser_nula").toString());
		}
	}

}
