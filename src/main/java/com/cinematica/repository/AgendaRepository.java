package com.cinematica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinematica.model.Agenda;

/**
 * AgendaRepository
 */
@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>, AgendaRepositoryCustom {

    @Query("FROM Agenda a WHERE a.id = ?1")
    Optional<Agenda> consultarAgendaPorIdPaciente(Long id);
}