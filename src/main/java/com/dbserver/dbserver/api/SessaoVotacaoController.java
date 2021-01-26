package com.dbserver.dbserver.api;

import com.dbserver.dbserver.dto.AbrirSessaoDto;
import com.dbserver.dbserver.entity.Pauta;
import com.dbserver.dbserver.entity.SessaoVotacao;
import com.dbserver.dbserver.entity.dominio.EnumStatusPauta;
import com.dbserver.dbserver.repository.PautaRepository;
import com.dbserver.dbserver.repository.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/sessao-votacao")
public class SessaoVotacaoController {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @PostMapping("/abrir-sessao")
    public ResponseEntity<String> abrirSessao(@RequestBody AbrirSessaoDto abrirSessaoDto) {
        Optional<Pauta> pauta = pautaRepository.findById(abrirSessaoDto.getIdPauta());
        if (pauta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Pauta informada não existe.");
        } else if (Objects.nonNull(pauta.get().getSessaoVotacao())) {
            return ResponseEntity.status(HttpStatus.OK).body("Está pauta já possuí sessão de votação vinculada.");
        }
        SessaoVotacao sessao = new SessaoVotacao();
        sessao.setDataCriacao(LocalDateTime.now());
        sessao.setStatus(EnumStatusPauta.ABERTA);
        sessao.setDuracaoEmMinuto(Objects.nonNull(abrirSessaoDto.getDuracaoEmMinuto()) ? abrirSessaoDto.getDuracaoEmMinuto() : 1L);

        sessaoVotacaoRepository.save(sessao);
        pauta.get().setSessaoVotacao(sessao);
        pautaRepository.save(pauta.get());
        return ResponseEntity.status(HttpStatus.OK).body("Sessão de votação aberta com sucesso.");
    }
}
