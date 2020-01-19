package com.cinematica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EspecialidadeDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EspecialidadeDTO {

	private Integer id;
	private String descricao;
	private String ativo;

}