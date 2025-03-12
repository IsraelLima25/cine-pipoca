package com.lima.api.cine.repository;

import com.lima.api.cine.enums.StatusPagamento;
import com.lima.api.cine.enums.StatusValidade;
import com.lima.api.cine.model.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    @Query("""
            FROM Ingresso ingresso JOIN FETCH
            ingresso.reserva reserva JOIN FETCH
            reserva.assento assento
            WHERE
            ingresso.statusValidade = :statusValidade
            AND
            ingresso.statusPagamento = :statusPagamento
            """)
    List<Ingresso> listarIngressosNaoExpirados(@Param("statusValidade") StatusValidade statusValidade,
                                               @Param("statusPagamento") StatusPagamento statusPagamento);

    Optional<Ingresso> findByUuid(String uuid);
}
