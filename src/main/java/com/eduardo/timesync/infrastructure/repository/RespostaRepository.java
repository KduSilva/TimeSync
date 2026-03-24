package com.eduardo.timesync.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.timesync.infrastructure.entity.Resposta;

public interface RespostaRepository extends JpaRepository< Resposta, Long >{
    
}
