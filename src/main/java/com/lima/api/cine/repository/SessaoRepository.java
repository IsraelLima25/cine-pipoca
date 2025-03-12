package com.lima.api.cine.repository;

import com.lima.api.cine.model.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Long> {

    @Query("""
            FROM Sessao sessao JOIN FETCH
            sessao.filme filme JOIN FETCH
            sessao.sala sala
            WHERE
            sessao.status=DISPONIVEL
            AND
            filme.uuid = :uuidFilme
            """)
    List<Sessao> listarSessoesDisponiveisPorUuidFilme(@Param("uuidFilme") String uuidFilme);

    Optional<Sessao> findByUuid(String uuid);

}
