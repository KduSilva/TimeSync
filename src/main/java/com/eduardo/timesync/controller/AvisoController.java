package com.eduardo.timesync.controller;

import org.springframework.web.bind.annotation.*;

import com.eduardo.timesync.infrastructure.entity.Aviso;
import com.eduardo.timesync.infrastructure.entity.Resposta;
import com.eduardo.timesync.service.AvisoService;

@RestController
@RequestMapping("/avisos")
public class AvisoController {

    private final AvisoService avisoService;

    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    @PostMapping
    public Aviso criarAviso(@RequestBody Aviso aviso) {
        return avisoService.criarAviso(aviso);
    }

    @GetMapping("/{id}")
    public Aviso buscarAviso(@PathVariable Long id) {
        return avisoService.buscarAviso(id);
    }

    @PostMapping("/{id}/resposta")
    public Resposta responderAviso(@PathVariable Long id, @RequestBody Resposta resposta) {
        return avisoService.responderAviso(id, resposta);
    }
}