package com.cinematica.dto;

import javax.validation.constraints.NotNull;

import com.cinematica.model.SimNao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * EspecialidadeDTO
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EspecialidadeDTO {

	private Integer id;
	@NotNull(message = "Campo Obrigatório")
	private String descricao;
	private SimNao ativo = SimNao.Sim;
	
	public EspecialidadeDTO(Integer id) {
		super();
		this.id = id;
	}
}