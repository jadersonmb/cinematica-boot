package com.cinematica.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cinematica.dto.ComboDTO;
import com.cinematica.dto.PessoaDTO;
import com.cinematica.model.Pessoa;

/**
 * PessoaMapper
 */
@Mapper(componentModel="spring")
public interface PessoaMapper {

    PessoaDTO toPessoaDTO(Pessoa entidade);
    
    @Mapping(source = "nome", target = "descricao")
    ComboDTO toPessoaSelectDTO(PessoaDTO entidade);
    
    Pessoa toPessoa(PessoaDTO entidadeDTO); 
    
}