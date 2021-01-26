package com.dbserver.dbserver.dto;

import lombok.Data;

@Data
public class VotoRequestDto {
    private Long idPauta;
    private String cpfAssociado;
    private Boolean resposta;
}
