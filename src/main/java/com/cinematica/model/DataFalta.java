package com.cinematica.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "data_falta")
public class DataFalta  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Date dataFalta;
	private Empresa empresa;
	private Configuracao configuracao;
	
	private Set<HorarioDisponivel> horarioDisponivel = new HashSet<HorarioDisponivel>();
	
	public DataFalta(){};
	
	public DataFalta(Date dataFalta, Configuracao configuracao, Empresa empresa) {
		this.dataFalta = dataFalta;
		this.empresa = empresa;
		this.configuracao = configuracao;
	}
	
	public DataFalta(Date dataFalta, Empresa empresa, Configuracao configuracao, Set<HorarioDisponivel> horarioDisponivel) {
		this.dataFalta = dataFalta;
		this.empresa = empresa;
		this.configuracao = configuracao;
		this.horarioDisponivel = horarioDisponivel;
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
	
	@Column(name = "data_falta", nullable = false)
	public Date getDataFalta() {
		return dataFalta;
	}

	public void setDataFalta(Date dataFalta) {
		this.dataFalta = dataFalta;
	}
	
	@ManyToOne
	@JoinColumn(name = "configuracao_id", nullable = false)
	public Configuracao getConfiguracao() {
		return configuracao;
	}
	
	public void setConfiguracao(Configuracao configuracao) {
		this.configuracao = configuracao;
	}
	
	@ManyToOne
	@JoinColumn(name = "empresas_id", nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dataFalta", cascade = CascadeType.ALL)
	public Set<HorarioDisponivel> getHorarioDisponivel() {
		return horarioDisponivel;
	}
	
	public void setHorarioDisponivel(Set<HorarioDisponivel> horarioDisponivel) {
		this.horarioDisponivel = horarioDisponivel;
	}

}
