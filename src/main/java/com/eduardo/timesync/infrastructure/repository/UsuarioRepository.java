package com.eduardo.timesync.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eduardo.timesync.infrastructure.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Exemplo de consulta personalizada para encontrar usuários por cargo
    List<Usuario> findByCargoNome(String nomeCargo);
    
}
