package com.cinematica.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cinematica.framework.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "empresas")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

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
	
	private Set<Pessoa> pessoas = new HashSet<Pessoa>();
	
	public Empresa() {}
	
	public Empresa(Integer id, String nomeFantasia, String razaoSocial, String cpnj, String email) {
		this.id = id;
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cpnj;
		this.email = email;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nome_fantasia") 
	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	@Column(name = "razao_social", nullable = false)
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@Column(name = "cnpj", nullable = false)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = StringUtil.removerMascaraDeCampo(cnpj, "./-");
	}

	@Embedded
	public EnderecoEmpresa getEndereco() {
		return endereco;
	}
	
	public void setEndereco(EnderecoEmpresa endereco) {
		this.endereco = endereco;
	}

	@Column(name = "telefone")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = StringUtil.removerMascaraDeCampo(this.telefone, "()-");
	}

	@Column(name = "fax")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = StringUtil.removerMascaraDeCampo(this.fax, "()-");
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "inscricao_estadual")
	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	@Column(name = "inscricao_municipal")
	public String getInscricaoMunicipal() {
		return inscricaoMunicipal;
	}

	public void setInscricaoMunicipal(String inscricaoMunicipal) {
		this.inscricaoMunicipal = inscricaoMunicipal;
	}

	@Column(name = "website")
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_contratacao")
	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
	public Set<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(Set<Pessoa> pessoas) {
		this.pessoas = pessoas;
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
		if (!(obj instanceof Empresa))
			return false;
		Empresa other = (Empresa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
