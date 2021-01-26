package com.dbserver.dbserver.repository;

import com.dbserver.dbserver.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    Optional<Voto> findByAssociadoIdAndPautaId(Long associadoId, Long pautaId);

    @Query("select count(v) from Voto v where v.resposta is false")
    public Integer getTotalVotoNao();

    @Query("select count(v) from Voto v where v.resposta is true")
    public Integer getTotalVotoSim();
}
