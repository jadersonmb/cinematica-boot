package com.cinematica.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
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

import com.cinematica.framework.contexto.Alterar;
import com.cinematica.framework.contexto.Cadastrar;
import com.cinematica.framework.util.StringUtil;
import com.cinematica.framework.util.VerificadorUtil;

@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String nomeCompleto;
	private Date criadoEm;
	private Date atualizadoEm = new Date();
	private TipoPessoa tipoPessoa;

	private Empresa empresa;
	private String cpf;
	private String rg;
	private Sexo sexo;
	private Endereco endereco = new Endereco();
	private String email;
	private String telefoneCelular;
	private String fotoUrl;
	private Integer numeroCalcado;
	private Profissao profissao;
	private SimNao funcionario = SimNao.Não;
	private Date dataNascimento;
	private String crefito; 
	private String telefone;
	private String indicacao;
	private Medico medico;
	private SimNao ativo = SimNao.Sim;
	
	public Pessoa() {}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nome", nullable = false, length = 100)
	@NotNull
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) { 
		this.nome = nome;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criado_em")
	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		if(VerificadorUtil.estaNulo(this.id)) {
			this.criadoEm = new Date();
		}
		this.criadoEm = criadoEm;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "atualizado_em")
	public Date getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(Date atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa", nullable = false)
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
	@ManyToOne
	@JoinColumn(name = "empresa_id", nullable = false)	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "cpf", length = 11)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = StringUtil.removerMascaraDeCampo(cpf, "./-");;
	}

	@Column(name = "rg", length = 25)
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo", nullable = false, length = 1)
	@NotNull(message = "erro_voce_deve_escolher_entre_masculino_ou_feminino_o_campo_nao_pode_ser_nulo", groups = {Cadastrar.class, Alterar.class})
	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	@Embedded
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Column(name = "email", length = 100)
	@NotNull
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "telefone_movel", length = 45)
	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}
	
	@Column(name = "telefone", length = 45)
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Column(name = "numero_calcado", nullable=false)
	public Integer getNumeroCalcado() {
		return numeroCalcado;
	}
	
	public void setNumeroCalcado(Integer numeroCalcado) {
		this.numeroCalcado = numeroCalcado;
	}
	
	@ManyToOne
	@JoinColumn(name = "profissao_id", nullable = false)
	public Profissao getProfissao() {
		return profissao;
	}
	
	public void setProfissao(Profissao profissao) {
		this.profissao = profissao;
	}
	
	@Column(name="crefito")
	public String getCrefito() {
		return crefito;
	}
	
	public void setCrefito(String crefito) {
		this.crefito = crefito;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "funcionario", nullable = false)
	public SimNao getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(SimNao funcionario) {
		this.funcionario = funcionario;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento")
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	@Column(name = "foto", length = 500) 
	public String getFotoUrl() {
		return fotoUrl;
	}

	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	
	@Column(name = "nome_completo", length = 500)
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	@Column(name = "indicacao")
	public String getIndicacao() {
		return indicacao;
	}

	public void setIndicacao(String indicacao) {
		this.indicacao = indicacao;
	}

	@ManyToOne
	@JoinColumn(name = "medico_id")
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	
	@Column(name = "ativo")
	public SimNao getAtivo() {
		return ativo;
	}
	
	public void setAtivo(SimNao ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Pessoa))
			return false;
		Pessoa other = (Pessoa) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
}
