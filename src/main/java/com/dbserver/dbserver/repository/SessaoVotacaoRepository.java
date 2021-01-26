package com.dbserver.dbserver.repository;

import com.dbserver.dbserver.entity.SessaoVotacao;
import com.dbserver.dbserver.entity.dominio.EnumStatusPauta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
    List<SessaoVotacao> findAllByStatus(EnumStatusPauta status);
}
