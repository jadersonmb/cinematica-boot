package com.cinematica.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.model.Pessoa;

/**
 * PessoaMapper
 */
@Mapper(componentModel="spring")
public interface PessoaMapper {

    PessoaDTO toPessoaDTO(Pessoa entidade); 
    
    Pessoa toPessoa(PessoaDTO entidadeDTO); 
    
}