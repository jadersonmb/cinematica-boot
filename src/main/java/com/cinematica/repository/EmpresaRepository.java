package com.cinematica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cinematica.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	@Query("FROM Empresa e WHERE e.nomeFantasia = ?1")
    Optional<Empresa> consultarPorNome(String nome);

}
