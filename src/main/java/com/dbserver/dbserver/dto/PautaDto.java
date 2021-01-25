package com.dbserver.dbserver.dto;

import com.dbserver.dbserver.entity.Associado;
import com.dbserver.dbserver.entity.SessaoVotacao;
import lombok.Data;

import java.util.List;

@Data
public class PautaDto {
    private Long id;
    private String descricao;
    private SessaoVotacao sessaoVotacao;
    private List<Associado> associados;
}
