package com.cinematica.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.cinematica.framework.util.StringUtil;

@Embeddable
public class EnderecoEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cep;
	private String endereco;
	private String bairro;
	private String cidade;
	private String estado;
	private Integer numero;
	private String complemento;
	private String pais;

	public EnderecoEmpresa() {
	}

	@Column(name = "cep", length = 8)
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = StringUtil.removerMascaraDeCampo(cep, "./-");;
	}

	@Column(name = "endereco", length = 150)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "bairro", length = 45)
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "cidade", length = 100)
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "estado", length = 2)
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "pais", length = 45)
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}
	
	@Column(name = "numero")
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	@Column(name = "complemento")
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
