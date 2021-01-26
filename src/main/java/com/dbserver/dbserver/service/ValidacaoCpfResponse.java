package com.dbserver.dbserver.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidacaoCpfResponse {
    @JsonProperty("status")
    private String status;
}
