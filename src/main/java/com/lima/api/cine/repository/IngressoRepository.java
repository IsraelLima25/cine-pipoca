package com.lima.api.cine.repository;

import com.lima.api.cine.enums.StatusValidade;
import com.lima.api.cine.model.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    @Query("FROM Ingresso ing JOIN FETCH ing.sessao ses JOIN FETCH ses.sala sal JOIN FETCH ing.cliente cli WHERE ing.statusValidade = :status")
    List<Ingresso> listarIngressosNaoExpirados(@Param("status") StatusValidade status);
}
