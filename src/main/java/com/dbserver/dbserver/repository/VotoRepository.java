package com.dbserver.dbserver.repository;

import com.dbserver.dbserver.entity.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VotoRepository extends JpaRepository<Voto, Long> {
    Optional<Voto> findByAssociadoIdAndPautaId(Long associadoId, Long pautaId);

    @Query("select count(v) from Voto v where v.resposta is false and v.pauta.id =:idPauta")
    public Integer getTotalVotoNao(@Param("idPauta") Long idPauta);

    @Query("select count(v) from Voto v where v.resposta is true and v.pauta.id =:idPauta")
    public Integer getTotalVotoSim(@Param("idPauta") Long idPauta);
}
