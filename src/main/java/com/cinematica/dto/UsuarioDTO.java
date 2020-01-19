package com.cinematica.dto;

import java.util.Date;

import com.cinematica.model.Pessoa;
import com.cinematica.model.SimNao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

	private Integer id;
	private Pessoa pessoa;
	private String login;
	private String senha;
	private Date ultimoAcesso = new Date();
	private SimNao ativo = SimNao.Sim;
	private String tokenAcesso;
	private boolean alterarSenha;
}
