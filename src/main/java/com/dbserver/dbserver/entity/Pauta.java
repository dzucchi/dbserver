package com.dbserver.dbserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "pauta")
public class Pauta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 800)
    private String descricao;

    @OneToOne
    @JoinColumn(name = "sessaoVotacao")
    private SessaoVotacao sessaoVotacao;
}
