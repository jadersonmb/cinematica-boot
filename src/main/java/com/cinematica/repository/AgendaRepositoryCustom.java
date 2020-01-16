package com.cinematica.repository;

import java.util.List;

import com.cinematica.model.Agenda;

/**
 * AgendaRepositoryCustom
 */
public interface AgendaRepositoryCustom {

    List<Agenda> consultarAgendaPorFuncionarioId(Integer id);
    
    List<Agenda> buscarAgendaDaSemanaPorPaciente(Integer id);
}