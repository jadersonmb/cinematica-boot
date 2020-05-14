package com.cinematica.services.formaPagamento;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cinematica.dto.FormaPagamentoDTO;
import com.cinematica.exception.FormaPagamentoException;

@Service
public interface FormaPagamentoService {

	public FormaPagamentoDTO buscarPorId(Long id) throws FormaPagamentoException;

	public FormaPagamentoDTO salvar(FormaPagamentoDTO entidadeDTO) throws FormaPagamentoException;

	public void delete(FormaPagamentoDTO entidadeDTO) throws FormaPagamentoException;

	public List<FormaPagamentoDTO> listarTodos() throws FormaPagamentoException;
}