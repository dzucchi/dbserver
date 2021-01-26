package com.dbserver.dbserver.service;

import com.dbserver.dbserver.entity.dominio.EnumStatusVoto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ValidarAssociadoService {

    private static final String URL_VALIDACAO_CPF = "https://user-info.herokuapp.com/users/";

    public EnumStatusVoto validarViaCpf(String cpf) {
        ValidacaoCpfResponse validacaoCpfResponse = obterTokenPlatform(cpf);
        return EnumStatusVoto.valueOf(validacaoCpfResponse.getStatus());
    }

    private ValidacaoCpfResponse obterTokenPlatform(String cpf) {
        RestTemplate rs = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        HttpEntity request = new HttpEntity<>(null, headers);
        ResponseEntity<ValidacaoCpfResponse> exchange;
        try {
            exchange = rs.exchange(URL_VALIDACAO_CPF + cpf, HttpMethod.GET, request, ValidacaoCpfResponse.class);
        } catch (Exception e) {
            log.error("erro ao validar CPF do Associado. ", e);
            return new ValidacaoCpfResponse("UNABLE_TO_VOTE");
        }
        return exchange.getBody();
    }
}
