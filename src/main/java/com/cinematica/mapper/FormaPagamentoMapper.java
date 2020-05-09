package com.cinematica.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.FormaPagamentoDTO;
import com.cinematica.model.FormaPagamento;

@Mapper(componentModel = "spring")
public interface FormaPagamentoMapper {

	FormaPagamentoDTO toFormaPagamentoDTO(FormaPagamento entidade);

	FormaPagamento toFormaPagamento(FormaPagamentoDTO entidadeDTO);
}
