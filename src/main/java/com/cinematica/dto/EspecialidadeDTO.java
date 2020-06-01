package com.cinematica.dto;

import javax.validation.constraints.NotNull;

import com.cinematica.model.SimNao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * EspecialidadeDTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EspecialidadeDTO {

	private Integer id;
	@NotNull(message = "Campo Obrigatório")
	private String descricao;
	private SimNao ativo = SimNao.Sim;

	public EspecialidadeDTO() {
	}
	
	public EspecialidadeDTO(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public SimNao getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}

}