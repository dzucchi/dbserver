package com.dbserver.dbserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PautaRequestDto {
    @JsonProperty(value = "descricao")
    private String descricao;
}
