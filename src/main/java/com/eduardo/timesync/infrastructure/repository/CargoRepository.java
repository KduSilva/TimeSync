package com.eduardo.timesync.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.timesync.infrastructure.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {
    // Métodos de consulta personalizados, se necessário
}
