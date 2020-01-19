package com.cinematica.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AgendaDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendaDTO {

	private Long id;
	@NotNull(message = "Campo Obrigatório")
	private Date dataInicio;
	@NotNull(message = "Campo Obrigatório")
	private Date dataFim;
	private PessoaDTO pessoa;
	private PessoaDTO funcionario;
	private boolean diaTodo;
	private boolean falta;
	private boolean cancelou;
	@NotNull(message = "Campo Obrigatório")
	private EspecialidadeDTO especialidade;
	@NotNull(message = "Campo Obrigatório")
	private HorarioDTO horario;
	
}