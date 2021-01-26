package com.dbserver.dbserver.dto;

import com.dbserver.dbserver.entity.SessaoVotacao;
import lombok.Data;

@Data
public class PautaDto {
    private Long id;
    private String descricao;
    private SessaoVotacao sessaoVotacao;
}
