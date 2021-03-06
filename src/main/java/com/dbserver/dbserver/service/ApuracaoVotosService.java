package com.dbserver.dbserver.service;

import com.dbserver.dbserver.dto.ApuracaoResponseDto;
import com.dbserver.dbserver.entity.Pauta;
import com.dbserver.dbserver.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApuracaoVotosService {

    @Autowired
    private VotoRepository votoRepository;

    public ApuracaoResponseDto apurar(Pauta pauta) {
        ApuracaoResponseDto dto = new ApuracaoResponseDto();
        dto.setId(pauta.getId());
        dto.setDescricao(pauta.getDescricao());
        dto.setVotosNao(votoRepository.getTotalVotoNao(pauta.getId()));
        dto.setVotosSim(votoRepository.getTotalVotoSim(pauta.getId()));
        if (dto.getVotosSim().compareTo(dto.getVotosNao()) > 0) {
            dto.setIsResultado("Aprovado");
        } else if (dto.getVotosSim().compareTo(dto.getVotosNao()) < 0) {
            dto.setIsResultado("Reprovado");
        } else {
            dto.setIsResultado("Empate");
        }
        return dto;
    }
}
