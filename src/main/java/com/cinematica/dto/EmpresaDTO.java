package com.cinematica.dto;

import java.util.Date;

import com.cinematica.model.EnderecoEmpresa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
	
}
