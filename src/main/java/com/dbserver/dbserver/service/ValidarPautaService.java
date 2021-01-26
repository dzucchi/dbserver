package com.dbserver.dbserver.service;

import com.dbserver.dbserver.entity.Pauta;
import com.dbserver.dbserver.entity.dominio.EnumStatusPauta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ValidarPautaService {

    public ResponseEntity<String> validarExisteAbertaFechada(Optional<Pauta> pauta) {
        if (pauta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Pauta informada não existe.");
        } else if (Objects.isNull(pauta.get().getSessaoVotacao())) {
            return ResponseEntity.status(HttpStatus.OK).body("A sessão para votação desta pauta não foi aberta.");
        } else if (pauta.get().getSessaoVotacao().getStatus().equals(EnumStatusPauta.FECHADA)) {
            return ResponseEntity.status(HttpStatus.OK).body("A sessão para votação desta pauta está encerrada.");
        }
        return null;
    }

    public ResponseEntity<Object> validarExisteAberta(Optional<Pauta> pauta) {
        if (pauta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Pauta informada não existe.");
        } else if (Objects.isNull(pauta.get().getSessaoVotacao())) {
            return ResponseEntity.status(HttpStatus.OK).body("A sessão para votação desta pauta não foi aberta.");
        }
        return null;
    }
}
