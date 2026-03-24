package com.eduardo.timesync.infrastructure.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.timesync.infrastructure.entity.Equipe;


public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    // Métodos de consulta personalizados
}