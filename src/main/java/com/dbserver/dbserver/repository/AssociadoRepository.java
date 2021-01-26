package com.dbserver.dbserver.repository;

import com.dbserver.dbserver.entity.Associado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    Associado findByCpf(String cpf);
}
