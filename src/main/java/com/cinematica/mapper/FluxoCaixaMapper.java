package com.cinematica.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.FluxoCaixaDTO;
import com.cinematica.model.FluxoCaixa;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Mapper(componentModel="spring")
public interface FluxoCaixaMapper {

    FluxoCaixaDTO toFluxoCaixaDTO(FluxoCaixa entidade);
    
    FluxoCaixa toFluxoCaixa(FluxoCaixaDTO entidadeDTO); 
    
}