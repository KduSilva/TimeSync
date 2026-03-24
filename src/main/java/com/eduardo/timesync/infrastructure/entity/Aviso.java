package com.eduardo.timesync.infrastructure.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Aviso {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private LocalDateTime dataHora;

    @ManyToOne
    private Usuario criador; // apenas ADMIN

    @ManyToMany
    private List<Equipe> equipesDestinatarias;

    // getters e setters


}
