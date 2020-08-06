package com.cinematica.services.formaPagamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cinematica.dto.FormaPagamentoDTO;
import com.cinematica.exception.FormaPagamentoException;

@Service
public interface FormaPagamentoService {

	public FormaPagamentoDTO buscarPorId(Long id) throws FormaPagamentoException;

	public FormaPagamentoDTO salvar(FormaPagamentoDTO entidadeDTO) throws FormaPagamentoException;

	public void delete(FormaPagamentoDTO entidadeDTO) throws FormaPagamentoException;

	public Page<FormaPagamentoDTO> listarTodos(Pageable pageable, FormaPagamentoFilterDTO filter) throws FormaPagamentoException;

	public void deleteList(List<Long> ids) throws FormaPagamentoException;
}
	