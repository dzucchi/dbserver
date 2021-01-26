package com.dbserver.dbserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ApuracaoResponseDto {
    @JsonProperty("ID")
    private Long id;
    @JsonProperty("DESCRICAO")
    private String descricao;
    @JsonProperty("VOTOS_SIM")
    private Integer votosSim;
    @JsonProperty("VOTOS_NAO")
    private Integer votosNao;
    @JsonProperty("RESULTADO")
    private String isResultado;
}

