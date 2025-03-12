package com.lima.api.cine.controller.response;

import com.lima.api.cine.enums.StatusSessao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SessaoResponse(
        String uuid,
        StatusSessao statusSessao,
        LocalDateTime dataHoraInicio,
        LocalDateTime dataHoraFim,
        FilmeResponse filmeResponse,
        SalaResponse salaResponse,
        BigDecimal valor
) {
}