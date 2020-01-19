package com.cinematica.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.cinematica.model.Agenda;

/**
 * AgendaRepositoryCustomImpl
 */
@Repository
public class AgendaRepositoryCustomImpl implements AgendaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Agenda> consultarAgendaPorFuncionarioId(Integer id) {
        return entityManager.createQuery("select a FROM Agenda a WHERE a.funcionario.id = :id", Agenda.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Agenda> buscarAgendaDaSemanaPorPaciente(Integer id) {
        return entityManager.createQuery(
                "select a FROM Agenda a WHERE a.dataInicio BETWEEN CURDATE() -7 AND CURDATE() AND a.pessoa.id = :id",Agenda.class)
                .setParameter("id", id)
                .getResultList();
    }
}