package com.cinematica.repository.horario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinematica.model.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {

}
