package com.cinematica.services.formaPagamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinematica.dto.FormaPagamentoDTO;
import com.cinematica.exception.FormaPagamentoException;
import com.cinematica.framework.util.Utils;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.mapper.FormaPagamentoMapper;
import com.cinematica.model.FormaPagamento;
import com.cinematica.repository.fluxoCaixa.FluxoCaixaRepository;
import com.cinematica.repository.formaPagamento.FormaPagamentoRepository;

@Service
public class FormaPagamentoServiceImpl implements FormaPagamentoService, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	@Autowired
	private FluxoCaixaRepository fluxoCaixaRepository;
	@Autowired
	private FormaPagamentoMapper mapper;

	public FormaPagamentoDTO buscarPorId(Long id) throws FormaPagamentoException {
		Optional<FormaPagamento> obj = formaPagamentoRepository.findById(id);
		FormaPagamentoDTO formaPagamentoDTO = mapper
				.toFormaPagamentoDTO(obj.orElseThrow(() -> new FormaPagamentoException()));
		return formaPagamentoDTO;
	}

	public FormaPagamentoDTO salvar(FormaPagamentoDTO entidadeDTO) throws FormaPagamentoException {
		regrasNegocioSalvar(entidadeDTO);
		FormaPagamento entidade = mapper.toFormaPagamento(entidadeDTO);
		FormaPagamento formaPagamento1 = formaPagamentoRepository.save(entidade);
		return mapper.toFormaPagamentoDTO(formaPagamento1);
	}

	public void delete(FormaPagamentoDTO entidadeDTO) throws FormaPagamentoException {
		regrasNegocioExcluir(entidadeDTO);
		formaPagamentoRepository.delete(mapper.toFormaPagamento(entidadeDTO));
	}
	
	protected void regrasNegocioExcluir(FormaPagamentoDTO entidade) {
		verificaSeExisteFluxoCaixaCadastrado(entidade);
	}
	

	public void regrasNegocioSalvar(FormaPagamentoDTO entidade) throws FormaPagamentoException {
		verificarCamposObrigatorios(entidade);
	}
	
	private void verificaSeExisteFluxoCaixaCadastrado(FormaPagamentoDTO entidade) throws FormaPagamentoException {
		Long numbers = this.fluxoCaixaRepository.verificaAgendaSeExisteFluxoPorPagamento(entidade.getId());
		if (VerificadorUtil.naoEstaNulo(numbers) && numbers > 0) {
			throw new FormaPagamentoException("existem_consultas_que_utilizam_esta_especialidade");
		}
	}

	public Page<FormaPagamentoDTO> listarTodos(Pageable pageable, FormaPagamentoFilterDTO filter)
			throws FormaPagamentoException {
		return formaPagamentoRepository.findAll((root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			if (Objects.nonNull(filter.getDescricao()) && !filter.getDescricao().isEmpty()) {
				predicates.add(builder.like(builder.lower(root.<String>get("descricao")),
						"%".concat(filter.getDescricao().toLowerCase()).concat("%")));
			}
			return builder.and(predicates.toArray(new Predicate[0]));
		}, pageable).map(mapper::toFormaPagamentoDTO);
	}
	
	@Override
	public void deleteList(List<Long> ids) throws FormaPagamentoException {
		ids.forEach(obj -> delete(new FormaPagamentoDTO(obj)));
	}

	public void verificarCamposObrigatorios(FormaPagamentoDTO entidade) throws FormaPagamentoException {
		if (VerificadorUtil.estaNuloOuVazio(entidade.getDescricao())) {
			throw new FormaPagamentoException(Utils
					.verificarSeCampoEstaNulo(entidade.getDescricao(), "erro_descricao_nao_pode_ser_nula").toString());
		}
	}
}
