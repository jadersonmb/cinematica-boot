package com.cinematica.interfaces;

import com.cinematica.dto.PessoaDTO;
import com.cinematica.model.Pessoa;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * PessoaMapper
 */
@Mapper(componentModel="spring")
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper( PessoaMapper.class );
 
    PessoaDTO pessoaToPessoaDTO(Pessoa pessoa); 
    
}