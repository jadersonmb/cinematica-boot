package com.cinematica.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "configuracao")
public class Configuracao implements Serializable, SetadorEmpresa{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Empresa empresa;
	private Pessoa  pessoa;
	private boolean segunda;
	private boolean terca;
	private boolean quarta;
	private boolean quinta;
	private boolean sexta;
	private boolean sabado;
	private boolean domingo;
	
	Set<DataFalta> dataFalta = new HashSet<DataFalta>();
	
	public Configuracao() {	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "empresa_id", nullable = false)	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@OneToOne(fetch= FetchType.LAZY)
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Column(name = "segunda")
	public boolean getSegunda() {
		return segunda;
	}
	
	public void setSegunda(boolean segunda) {
		this.segunda = segunda;
	}

	@Column(name = "terca")
	public boolean getTerca() {
		return terca;
	}

	public void setTerca(boolean terca) {
		this.terca = terca;
	}

	@Column(name = "quarta")
	public boolean getQuarta() {
		return quarta;
	}

	public void setQuarta(boolean quarta) {
		this.quarta = quarta;
	}

	@Column(name = "quinta")
	public boolean getQuinta() {
		return quinta;
	}

	public void setQuinta(boolean quinta) {
		this.quinta = quinta;
	}

	@Column(name = "sexta")
	public boolean getSexta() {
		return sexta;
	}

	public void setSexta(boolean sexta) {
		this.sexta = sexta;
	}

	@Column(name = "sabado")
	public boolean getSabado() {
		return sabado;
	}

	public void setSabado(boolean sabado) {
		this.sabado = sabado;
	}

	@Column(name = "domingo")
	public boolean getDomingo() {
		return domingo;
	}

	public void setDomingo(boolean domingo) {
		this.domingo = domingo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "configuracao")
	public Set<DataFalta> getDataFalta() {
		return dataFalta;
	}

	public void setDataFalta(Set<DataFalta> dataFalta) {
		this.dataFalta = dataFalta;
	}
	
	
}
