package com.cinematica.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable,SetadorEmpresa {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataInicio;
	private Date dataFim;
	private Pessoa pessoa;
	private Pessoa funcionario;
	private boolean diaTodo;
	private boolean falta;
	private boolean cancelou;
	private Especialidade especialidade;
	private Empresa empresa;
	private Horario horario;
	private SolicitacaoAgenda solicitacaoAgenda;

	public Agenda() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio")
	public Date getDataInicio() {
		return dataInicio;
	}
	
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim")
	public Date getDataFim() {
		return dataFim;
	}
	
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	
	@Column(name = "dia_todo")
	public boolean isDiaTodo() {
		return diaTodo;
	}
	
	public void setDiaTodo(boolean diaTodo) {
		this.diaTodo = diaTodo;
	}
	
	@Column(name = "falta")
	public boolean isFalta() {
		return falta;
	}
	
	public void setFalta(boolean falta) {
		this.falta = falta;
	}
	
	@Column(name = "cancelou")
	public boolean isCancelou() {
		return cancelou;
	}
	
	public void setCancelou(boolean cancelou) {
		this.cancelou = cancelou;
	}
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@ManyToOne
	@JoinColumn(name = "pessoa_funcionario_id", nullable = false)	
	public Pessoa getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Pessoa funcionario) {
		this.funcionario = funcionario;
	}

	@ManyToOne
	@JoinColumn(name = "especialidade_id", nullable = false)	
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
	@ManyToOne
	@JoinColumn(name = "solicitacao_id")
	public SolicitacaoAgenda getSolicitacaoAgenda() {
		return solicitacaoAgenda;
	}
	
	public void setSolicitacaoAgenda(SolicitacaoAgenda solicitacaoAgenda) {
		this.solicitacaoAgenda = solicitacaoAgenda;
	}

	@ManyToOne
	@JoinColumn(name = "empresa_id", nullable = false)	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@ManyToOne
	@JoinColumn(name = "horario_id", nullable = false)
	public Horario getHorario() {
		return horario;
	}
	
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
}
