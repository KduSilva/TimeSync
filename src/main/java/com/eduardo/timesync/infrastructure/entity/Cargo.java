package com.eduardo.timesync.infrastructure.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome; // Adm, Lider, Membro.

    private String descricao; // Descrição do corpo de membros (opcional para explicar responsabilidades).

    // getters e setters
}
