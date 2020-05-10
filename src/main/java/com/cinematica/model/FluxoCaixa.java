package com.cinematica.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.cinematica.enums.TipoLancamento;
import com.cinematica.framework.contexto.Alterar;
import com.cinematica.framework.contexto.Cadastrar;

@Entity
@Table(name = "fluxo_caixa")
public class FluxoCaixa implements Serializable, SetadorEmpresa {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Date dataLancamento = new Date();
	private Empresa empresa;
	private Usuario usuario;
	private String descricao;
	private BigDecimal valor;
	private TipoLancamento tipoLancamento;
	private Especialidade especialidade;
	private FormaPagamento formaPagamento;
	private Pessoa pessoa;
	private String numeroRecibo;
	private Integer quantidadeParcela;

	public FluxoCaixa() {};
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "descricao")
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "valor")
	@NotNull(message = "erro_valor_nao_pode_ser_nula", groups = {Cadastrar.class, Alterar.class})
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_lancamento", nullable = false)
	@NotNull(message = "erro_voce_deve_escolher_entre_receita_ou_despesas_o_campo_nao_pode_ser_nulo", groups = {Cadastrar.class, Alterar.class})
	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	
	@Column(name = "quantidade_parcela")
	public Integer getQuantidadeParcela() {
		return quantidadeParcela;
	}
	
	public void setQuantidadeParcela(Integer quantidadeParcela) {
		this.quantidadeParcela = quantidadeParcela;
	}
	
	@ManyToOne
	@JoinColumn(name = "forma_pagamento_id",  nullable = false)
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data_lancamento", nullable = false)
	public Date getDataLancamento() {
		return dataLancamento;
	}
	
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	@Column(name = "numero_recibo")
	public String getNumeroRecibo() {
		return numeroRecibo;
	}
	
	public void setNumeroRecibo(String numeroRecibo) {
		this.numeroRecibo = numeroRecibo;
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
	@JoinColumn(name = "pessoa_id")
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	@ManyToOne
	@JoinColumn(name = "especialidade_id")
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FluxoCaixa))
			return false;
		FluxoCaixa other = (FluxoCaixa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
