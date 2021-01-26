package com.dbserver.dbserver.service;

import com.dbserver.dbserver.entity.SessaoVotacao;
import com.dbserver.dbserver.entity.dominio.EnumStatusPauta;
import com.dbserver.dbserver.repository.SessaoVotacaoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class FecharApuracaoService {

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Scheduled(cron = "0 * * * * *")
    public void start() {
        try {
            log.info("[FECHAR APUTAÇÃO] Processo iniciado.");
            List<SessaoVotacao> sessaoVotacaoList = sessaoVotacaoRepository.findAllByStatus(EnumStatusPauta.ABERTA);
            log.info("[FECHAR APUTAÇÃO] Registros encontrados: {}.", sessaoVotacaoList.size());
            sessaoVotacaoList.stream().forEach(sessaoVotacao -> {
                if (sessaoVotacao.getDataCriacao().plusMinutes(sessaoVotacao.getDuracaoEmMinuto()).compareTo(LocalDateTime.now()) < 0) {
                    sessaoVotacao.setStatus(EnumStatusPauta.FECHADA);
                    sessaoVotacaoRepository.save(sessaoVotacao);
                }
            });
            log.info("[FECHAR APUTAÇÃO] Processo finalizado.");
        } catch (Exception e) {
            log.error("[FECHAR APUTAÇÃO] Ocorreu um erro: ", e);
        }
    }
}
