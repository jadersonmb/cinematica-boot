package com.cinematica.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cinematica.model.Agenda;

/**
 * AgendaRepositoryCustom
 */
@Repository
public interface AgendaRepositoryCustom {

    List<Agenda> consultarAgendaPorFuncionarioId(Integer id);
    
    List<Agenda> consultarAgendaDaSemanaPorPaciente(Integer id);
    
    Agenda consultarAgendaPorPaciente(Integer id);
}