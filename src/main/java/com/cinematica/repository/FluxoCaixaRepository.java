package com.cinematica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinematica.model.FluxoCaixa;

@Repository
public interface FluxoCaixaRepository extends JpaRepository<FluxoCaixa, Long>, FluxoCaiaxRepositoryCustom {

}
