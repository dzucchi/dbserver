package com.dbserver.dbserver.api;

import com.dbserver.dbserver.dto.VotoRequestDto;
import com.dbserver.dbserver.entity.Associado;
import com.dbserver.dbserver.entity.Pauta;
import com.dbserver.dbserver.entity.Voto;
import com.dbserver.dbserver.entity.dominio.EnumStatusVoto;
import com.dbserver.dbserver.repository.AssociadoRepository;
import com.dbserver.dbserver.repository.PautaRepository;
import com.dbserver.dbserver.repository.VotoRepository;
import com.dbserver.dbserver.service.ValidarAssociadoService;
import com.dbserver.dbserver.service.ValidarPautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/voto")
public class VotoController {

    @Autowired
    private VotoRepository votoRepository;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private ValidarAssociadoService validarAssociadoService;

    @Autowired
    private ValidarPautaService validarPautaService;

    @PostMapping("/votar")
    public ResponseEntity<String> votar(@RequestBody VotoRequestDto votoDto) {
        Optional<Pauta> pauta = pautaRepository.findById(votoDto.getIdPauta());
        ResponseEntity<String> validacaoPauta = validarPautaService.validarExisteAbertaFechada(pauta);
        if (Objects.nonNull(validacaoPauta)) {
            return validacaoPauta;
        }
        // VALIDAÇÃO DO CPF ASSOCIADO.
        Associado associado = associadoRepository.findByCpf(votoDto.getCpfAssociado());
        if (Objects.isNull(associado)) {
            EnumStatusVoto enumStatusVoto = validarAssociadoService.validarViaCpf(votoDto.getCpfAssociado());
            if (enumStatusVoto.equals(EnumStatusVoto.ABLE_TO_VOTE)) {
                associado = new Associado();
                associado.setCpf(votoDto.getCpfAssociado());
                associadoRepository.save(associado);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body("CPF do associado não está de acordo.");
            }
        } else {
            // VERIFICANDO SE ASSOCIADO JÁ VOTO PARA ESTA PAUTA.
            if (votoRepository.findByAssociadoIdAndPautaId(associado.getId(), pauta.get().getId()).isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body("Este associado já votou para está pauta.");
            }
        }
        Voto v = new Voto();
        v.setAssociado(associado);
        v.setPauta(pauta.get());
        v.setResposta(votoDto.getResposta());
        votoRepository.save(v);
        return ResponseEntity.status(HttpStatus.OK).body("Voto efetivado.");
    }

}
