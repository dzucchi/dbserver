package com.dbserver.dbserver.dto;

import com.dbserver.dbserver.entity.Associado;
import com.dbserver.dbserver.entity.Pauta;
import lombok.Data;

@Data
public class VotoDto {
    private Long id;
    private Associado associado;
    private Pauta pauta;
    private Boolean resposta;
}
