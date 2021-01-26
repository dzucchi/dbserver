package com.dbserver.dbserver.entity.dominio;

import lombok.Getter;

@Getter
public enum EnumStatusPauta {
    ABERTA("Aberta"),
    FECHADA("Fechada");

    private String nome;

    EnumStatusPauta(String nome) {
        this.nome = nome;
    }
}
