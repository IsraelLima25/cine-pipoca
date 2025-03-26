package com.lima.api.cine.api.request;

import com.lima.api.cine.enums.FormaPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PagamentoRequest(
        @NotBlank
        String uuidOrigem,

        @NotNull
        FormaPagamento formaPagamento,

        @NotNull
        @Positive
        BigDecimal valor
) { }
