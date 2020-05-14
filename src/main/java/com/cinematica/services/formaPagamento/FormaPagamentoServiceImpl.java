package com.cinematica.services.formaPagamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinematica.dto.FormaPagamentoDTO;
import com.cinematica.exception.EspecialidadeException;
import com.cinematica.exception.FormaPagamentoException;
import com.cinematica.framework.util.VerificadorUtil;
import com.cinematica.mapper.FormaPagamentoMapper;
import com.cinematica.model.FormaPagamento;
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
	private FormaPagamentoMapper mapper;

	public FormaPagamentoDTO buscarPorId(Long id) throws FormaPagamentoException {
		Optional<FormaPagamento> obj = formaPagamentoRepository.findById(id);
		FormaPagamentoDTO formaPagamentoDTO = mapper
				.toFormaPagamentoDTO(obj.orElseThrow(() -> new FormaPagamentoException()));
		return formaPagamentoDTO;
	}

	public FormaPagamentoDTO salvar(FormaPagamentoDTO entidadeDTO) throws FormaPagamentoException {
		
		if(VerificadorUtil.estaNulo(entidadeDTO.getDescricao())) {
			throw new EspecialidadeException("erro_descricao_nao_pode_ser_nula");
		}
		
		FormaPagamento entidade = mapper.toFormaPagamento(entidadeDTO);
		FormaPagamento formaPagamento1 = formaPagamentoRepository.save(entidade);
		return mapper.toFormaPagamentoDTO(formaPagamento1);
	}

	public void delete(FormaPagamentoDTO entidadeDTO) throws FormaPagamentoException {
		formaPagamentoRepository.delete(mapper.toFormaPagamento(entidadeDTO));
	}

	public List<FormaPagamentoDTO> listarTodos() throws FormaPagamentoException {
		List<FormaPagamento> listaFormaPagamentos = formaPagamentoRepository.findAll();
		List<FormaPagamentoDTO> listaFormaPagamentoDTO = new ArrayList<>();
		listaFormaPagamentos.forEach(p-> listaFormaPagamentoDTO.add(mapper.toFormaPagamentoDTO(p)));
		return  listaFormaPagamentoDTO;
	}
}
