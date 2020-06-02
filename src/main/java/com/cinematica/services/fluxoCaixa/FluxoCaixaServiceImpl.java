package com.cinematica.services.fluxoCaixa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cinematica.dto.FluxoCaixaDTO;
import com.cinematica.exception.FluxoCaixaException;
import com.cinematica.framework.util.Utils;
import com.cinematica.mapper.FluxoCaixaMapper;
import com.cinematica.model.FluxoCaixa;
import com.cinematica.repository.fluxoCaixa.FluxoCaixaRepository;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Service
public class FluxoCaixaServiceImpl implements FluxoCaixaService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private FluxoCaixaRepository fluxoCaixaRepository;
	@Autowired
	private FluxoCaixaMapper mapper;
	
	private void regrasNegocioSalvar(FluxoCaixaDTO entidade) throws FluxoCaixaException {
		verificarCamposObrigatorios(entidade);
	}

	public FluxoCaixaDTO buscarPorId(Long id) throws FluxoCaixaException {
		Optional<FluxoCaixa> obj = fluxoCaixaRepository.findById(id);
		if(!obj.isPresent()) {
			throw new FluxoCaixaException("erro_fluxo_nao_existe");
		}
		FluxoCaixaDTO fluxoCaixaDTO = mapper.toFluxoCaixaDTO(obj.orElseThrow(() -> new FluxoCaixaException()));
		return fluxoCaixaDTO;
	}

	public FluxoCaixaDTO salvar(FluxoCaixaDTO entidadeDTO) throws FluxoCaixaException {
		
		regrasNegocioSalvar(entidadeDTO);
		
		FluxoCaixa entidade = mapper.toFluxoCaixa(entidadeDTO);
		FluxoCaixa fluxoCaixa = fluxoCaixaRepository.save(entidade);
		return mapper.toFluxoCaixaDTO(fluxoCaixa);
	}

	public void delete(FluxoCaixaDTO entidadeDTO) throws FluxoCaixaException {
		fluxoCaixaRepository.delete(mapper.toFluxoCaixa(entidadeDTO));
	}

	public List<FluxoCaixaDTO> listarTodos() throws FluxoCaixaException {
		List<FluxoCaixa> listaFluxoCaixa = fluxoCaixaRepository.findAll();
		List<FluxoCaixaDTO> listaFluxoCaixaDTO = new ArrayList<>();
		listaFluxoCaixa.forEach(p -> listaFluxoCaixaDTO.add(mapper.toFluxoCaixaDTO(p)));
		return listaFluxoCaixaDTO;
	}
	
	public void verificarCamposObrigatorios(FluxoCaixaDTO entidade) throws FluxoCaixaException {

		StringBuffer msg = new StringBuffer();
		msg.append(Utils.verificarSeCampoEstaNulo(entidade.getValor(), "erro_valor_nao_pode_ser_nula;"));
		msg.append(Utils.verificarSeCampoEstaNulo(entidade.getTipoLancamento(),"erro_voce_deve_escolher_entre_receita_ou_despesas_o_campo_nao_pode_ser_nulo;"));
		msg.append(Utils.verificarSeCampoEstaNulo(entidade.getFormaPagamento(), "erro_forma_de_pagamento_nao_pode_ser_nula;"));

		if (msg.length() > 0) {
			throw new FluxoCaixaException(msg.toString());
		}
	}

	@Override
	public Page<FluxoCaixaDTO> listarTodosPages(Integer page, Integer linePage, String orderBy, String direction)
			throws FluxoCaixaException {
		Page<FluxoCaixa> listaFluxoCaixa = fluxoCaixaRepository.findAll(PageRequest.of(page, linePage, Direction.valueOf(direction), orderBy));
		Page<FluxoCaixaDTO> listaFluxoCaixaDTO = listaFluxoCaixa.map(obj -> mapper.toFluxoCaixaDTO(obj));
		return listaFluxoCaixaDTO;
	}

	@Override
	public void deleteList(List<Long> ids) throws FluxoCaixaException {
		ids.forEach(obj -> delete(new FluxoCaixaDTO(obj)));
	}

}
