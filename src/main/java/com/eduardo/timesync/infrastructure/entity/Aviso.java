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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Usuario getCriador() {   // <-- corrigido
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public List<Equipe> getEquipesDestinatarias() {
        return equipesDestinatarias;
    }

    public void setEquipesDestinatarias(List<Equipe> equipesDestinatarias) {
        this.equipesDestinatarias = equipesDestinatarias;
    }
}