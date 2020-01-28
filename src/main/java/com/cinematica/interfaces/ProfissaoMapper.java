package com.cinematica.interfaces;

import org.mapstruct.Mapper;
/**
 * 
 * @author Jaderson Morais
 *
 */

import com.cinematica.dto.ProfissaoDTO;
import com.cinematica.model.Profissao;

@Mapper(componentModel = "spring")
public interface ProfissaoMapper {

	ProfissaoDTO toProfissaoDTO(Profissao entidade);

	Profissao toProfissao(ProfissaoDTO entidadeDTO);

}
