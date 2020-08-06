package com.cinematica.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.ComboDTO;
import com.cinematica.dto.FormaPagamentoDTO;
import com.cinematica.model.FormaPagamento;

@Mapper(componentModel = "spring")
public interface FormaPagamentoMapper {

	FormaPagamentoDTO toFormaPagamentoDTO(FormaPagamento entidade);
	
	ComboDTO toComboDTO(FormaPagamentoDTO entidade);
	
	FormaPagamento toFormaPagamento(FormaPagamentoDTO entidadeDTO);
}
