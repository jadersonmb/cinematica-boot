package com.cinematica.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PessoaDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotNull(message = "Campo Obrigatório")
	private String nome;
	private String nomeCompleto;
	private Date criadoEm;
	private String tipoPessoa;

	private String cpf;
	private String rg;
	private String sexo;
	private String email;
	private String telefoneCelular;
	private String fotoUrl;
	private String funcionario;
	private Date dataNascimento;
	private String telefone;

}