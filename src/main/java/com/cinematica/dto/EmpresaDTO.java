package com.cinematica.dto;

import java.util.Date;

import com.cinematica.model.EnderecoEmpresa;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author Jaderson Morais
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpresaDTO {

	private Integer id;
	private String nomeFantasia;
	private String razaoSocial;
	private String cnpj;
	private EnderecoEmpresa endereco = new EnderecoEmpresa();;
	private String telefone;
	private String fax;
	private String email;
	private String inscricaoEstadual;
	private String inscricaoMunicipal;
	private String website;
	private Date dataContratacao = new Date();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public EnderecoEmpresa getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoEmpresa endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

}
