package com.eduardo.timesync.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.eduardo.timesync.infrastructure.entity.Aviso;
import com.eduardo.timesync.infrastructure.entity.Resposta;
import com.eduardo.timesync.infrastructure.repository.AvisoRepository;
import com.eduardo.timesync.infrastructure.repository.RespostaRepository;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    public Aviso criarAviso(Aviso aviso) {
        // regra: apenas ADMIN pode criar
        if (!"ADM".equals(aviso.getCriador().getCargo().getNome())) {
            throw new RuntimeException("Somente ADM pode criar avisos.");
        }
        return avisoRepository.save(aviso);
    }

    public Aviso buscarAviso(Long id) {
        return avisoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aviso não encontrado"));
    }

    public Resposta responderAviso(Long id, Resposta resposta) {
        if ("RECUSADO".equals(resposta.getStatus()) &&
            (resposta.getJustificativa() == null || resposta.getJustificativa().isEmpty())) {
            throw new RuntimeException("Justificativa obrigatória ao recusar.");
        }

        Aviso aviso = buscarAviso(id);
        resposta.setAviso(aviso);

        return respostaRepository.save(resposta);
    }
}