package com.dbserver.dbserver.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pauta")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 800)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sessaoVotacao", nullable = false)
    private SessaoVotacao sessaoVotacao;

    @ManyToMany
    @JoinTable(
            name = "pauta_associados",
            joinColumns = @JoinColumn(name = "pauta_id"),
            inverseJoinColumns = @JoinColumn(name = "associado_id")
    )
    private List<Associado> associados;
}
