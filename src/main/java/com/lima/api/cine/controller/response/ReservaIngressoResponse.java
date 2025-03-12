package com.lima.api.cine.controller.response;

import com.lima.api.cine.enums.FormaPagamento;
import com.lima.api.cine.enums.StatusPagamento;
import com.lima.api.cine.enums.StatusValidade;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ReservaIngressoResponse(
    String uuid,
    boolean isMeiaEntrada,
    BigDecimal valorTotal,
        FormaPagamento formaPagamento,
        LocalDateTime expiraEm,
        StatusValidade statusValidade,
        StatusPagamento statusPagamento,
        String nomeFilme,
        String nomeSala,
        int numeroAssento
) {
}