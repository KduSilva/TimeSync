package com.eduardo.timesync.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.timesync.infrastructure.entity.Aviso;

public interface AvisoRepository extends JpaRepository<Aviso, Long> {
    // Métodos de consulta personalizados
    
}
