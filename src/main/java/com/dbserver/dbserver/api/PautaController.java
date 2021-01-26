package com.dbserver.dbserver.api;

import com.dbserver.dbserver.dto.PautaDto;
import com.dbserver.dbserver.dto.PautaRequestDto;
import com.dbserver.dbserver.entity.Pauta;
import com.dbserver.dbserver.entity.dominio.EnumStatusPauta;
import com.dbserver.dbserver.repository.PautaRepository;
import com.dbserver.dbserver.service.ApuracaoVotosService;
import com.dbserver.dbserver.service.ValidarPautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/pauta")
public class PautaController {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private ApuracaoVotosService apuracaoVotosService;

    @Autowired
    private ValidarPautaService validarPautaService;

    @GetMapping("/obter-pauta")
    public List<Pauta> listarPautas() {
        return pautaRepository.findAll();
    }

    @GetMapping("/obter-pauta/{id}")
    public ResponseEntity<Object> obterPauta(@PathVariable Long id) {
        Optional<Pauta> pauta = pautaRepository.findById(id);
        ResponseEntity<Object> validacaoPauta = validarPautaService.validarExisteAberta(pauta);
        if (Objects.nonNull(validacaoPauta)) {
            return validacaoPauta;
        }
        if (pauta.get().getSessaoVotacao().getStatus().equals(EnumStatusPauta.ABERTA)) {
            return ResponseEntity.status(HttpStatus.OK).body("A sessão para votação está aberta com encerramento previsto para " +
                    pauta.get().getSessaoVotacao().getDataCriacao().plusMinutes(pauta.get().getSessaoVotacao().getDuracaoEmMinuto())
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        } else if (pauta.get().getSessaoVotacao().getStatus().equals(EnumStatusPauta.FECHADA)) {
            return ResponseEntity.status(HttpStatus.OK).body(apuracaoVotosService.apurar(pauta.get()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao obter pauta.");
    }

    @PostMapping("/nova-pauta")
    public ResponseEntity<String> abrirPauta(@RequestBody PautaRequestDto pautaDto) {
        Pauta entity = new Pauta();
        entity.setDescricao(pautaDto.getDescricao());
        pautaRepository.save(entity);
        return ResponseEntity.status(HttpStatus.OK).body("Pauta incluida com sucesso.");
    }
}
