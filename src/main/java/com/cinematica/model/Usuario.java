package com.cinematica.model;

import java.io.Serializable;
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
import javax.persistence.Transient;

import org.hibernate.envers.NotAudited;


@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Pessoa pessoa;
	private String login;
	private String senha;
	private Date ultimoAcesso = new Date();
	private SimNao ativo = SimNao.Sim;
	private String tokenAcesso;
	private boolean alterarSenha;
	
	
	public Usuario() {}

	
	public Usuario(Integer id, Pessoa pessoa, String login, String senha,
			SimNao ativo, String tokenAcesso) {
		this.id = id;
		this.pessoa = pessoa;
		this.login = login;
		this.senha = senha;
		this.ativo = ativo;
		this.tokenAcesso = tokenAcesso;
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

	@ManyToOne
	@JoinColumn(name = "pessoa_id", nullable = false)
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Column(name= "login", length = 45)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name= "senha", length = 300)
	@NotAudited
	public String getSenha() {
		return senha;
	}	

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "ultimo_acesso", nullable = false)
	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "ativo", nullable = false)
	public SimNao getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}
	
	public void setAlterarSenha(boolean alterarSenha) {
		this.alterarSenha = alterarSenha;
	}
	
	@Transient
	public boolean isAlterarSenha() {
		return alterarSenha;
	}

	@Column(name = "token_acesso", length = 1000)
	public String getTokenAcesso() {
		return tokenAcesso;
	}
	
	public void setTokenAcesso(String tokenAcesso) {
		this.tokenAcesso = tokenAcesso;
	}
	
	@Transient
	public Empresa getEmpresa() {
		return this.getPessoa().getEmpresa();
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
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
