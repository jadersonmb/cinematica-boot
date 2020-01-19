package com.cinematica.interfaces;

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
	
    EmpresaDTO toEmpresaDTO(Empresa empresa); 
    
    Empresa toEmpresa(EmpresaDTO empresaDTO);
}
