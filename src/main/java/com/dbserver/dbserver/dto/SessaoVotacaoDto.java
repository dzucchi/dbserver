package com.dbserver.dbserver.dto;

import com.dbserver.dbserver.entity.dominio.EnumStatusPauta;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SessaoVotacaoDto {
    private Long id;
    private LocalDateTime dataCriacao;
    private Long duracaoEmMinuto;
    private EnumStatusPauta status;
}
