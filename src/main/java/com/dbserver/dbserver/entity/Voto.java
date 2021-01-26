package com.dbserver.dbserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "voto")
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associado", nullable = false)
    private Associado associado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta")
    private Pauta pauta;

    @JoinColumn(name = "resposta")
    private Boolean resposta;
}
