package com.cinematica.mapper;

import org.mapstruct.Mapper;

import com.cinematica.dto.EmpresaDTO;
import com.cinematica.model.Empresa;

/**
 * 
 * @author Jaderson Morais
 *
 */
@Mapper(componentModel="spring")
public interface EmpresaMapper {
	
    EmpresaDTO toEmpresaDTO(Empresa entidade); 
    
    Empresa toEmpresa(EmpresaDTO entidadeDTO);
}
