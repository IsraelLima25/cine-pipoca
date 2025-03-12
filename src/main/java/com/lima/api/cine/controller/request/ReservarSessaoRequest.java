package com.lima.api.cine.controller.request;

import com.lima.api.cine.enums.FormaPagamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

public record ReservarSessaoRequest(

    @UUID
    @NotBlank
    String uuidSessao,

    @NotNull
    @Positive
    int numeroAssento,

    @NotNull
    boolean isMeiaEntrada,

    @NotNull
    FormaPagamento formaPagamento
) { }
