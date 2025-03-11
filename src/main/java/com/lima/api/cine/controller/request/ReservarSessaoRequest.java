package com.lima.api.cine.controller.request;

import com.lima.api.cine.enums.FormaPagamento;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ReservarSessaoRequest(

    @NotNull
    @Positive
    Long idSessao,

    @NotNull
    @Positive
    int numeroAssento,

    @NotNull
    boolean isMeiaEntrada,

    @NotNull
    FormaPagamento formaPagamento
) { }
