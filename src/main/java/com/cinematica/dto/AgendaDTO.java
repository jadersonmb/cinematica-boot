package com.cinematica.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

/**
 * AgendaDTO
 */
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public PessoaDTO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(PessoaDTO funcionario) {
		this.funcionario = funcionario;
	}

	public boolean isDiaTodo() {
		return diaTodo;
	}

	public void setDiaTodo(boolean diaTodo) {
		this.diaTodo = diaTodo;
	}

	public boolean isFalta() {
		return falta;
	}

	public void setFalta(boolean falta) {
		this.falta = falta;
	}

	public boolean isCancelou() {
		return cancelou;
	}

	public void setCancelou(boolean cancelou) {
		this.cancelou = cancelou;
	}

	public EspecialidadeDTO getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(EspecialidadeDTO especialidade) {
		this.especialidade = especialidade;
	}

	public HorarioDTO getHorario() {
		return horario;
	}

	public void setHorario(HorarioDTO horario) {
		this.horario = horario;
	}

}