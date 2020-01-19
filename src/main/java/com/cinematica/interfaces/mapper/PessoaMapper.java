package com.cinematica.interfaces.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.model.Pessoa;

/**
 * PessoaMapper
 */
@Mapper(componentModel="spring")
public interface PessoaMapper {

    PessoaDTO toPessoaDTO(Pessoa pessoa); 
    
}