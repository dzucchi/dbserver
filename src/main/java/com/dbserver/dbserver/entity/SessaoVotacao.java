package com.dbserver.dbserver.entity;

import com.dbserver.dbserver.entity.dominio.EnumStatusPauta;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sessao_votacao")
public class SessaoVotacao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "duracao_em_minuto")
    @ColumnDefault("1")
    private Long duracaoEmMinuto;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumStatusPauta status;
}
