package com.cinematica.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.cinematica.model.SimNao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * PessoaDTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotNull(message = "Campo Obrigatório")
	private String nome;
	private String nomeCompleto;
	private Date criadoEm;
	private String tipoPessoa;
	private EmpresaDTO empresa;
	private EnderecoDTO endereco;
	private ProfissaoDTO profissao;
	private String cpf;
	private String rg;
	private String sexo;
	private String email;
	private String telefoneCelular;
	private String fotoUrl;
	private Date dataNascimento;
	private String telefone;
	private Integer numeroCalcado;
	private String crefito;
	private SimNao funcionario = SimNao.Não;
	private SimNao ativo = SimNao.Sim;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}

	public ProfissaoDTO getProfissao() {
		return profissao;
	}

	public void setProfissao(ProfissaoDTO profissao) {
		this.profissao = profissao;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getNumeroCalcado() {
		return numeroCalcado;
	}

	public void setNumeroCalcado(Integer numeroCalcado) {
		this.numeroCalcado = numeroCalcado;
	}

	public String getCrefito() {
		return crefito;
	}

	public void setCrefito(String crefito) {
		this.crefito = crefito;
	}

	public SimNao getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(SimNao funcionario) {
		this.funcionario = funcionario;
	}

	public SimNao getAtivo() {
		return ativo;
	}

	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}

}