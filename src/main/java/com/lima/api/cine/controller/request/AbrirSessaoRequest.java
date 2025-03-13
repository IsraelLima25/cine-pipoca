package com.lima.api.cine.controller.request;

import com.lima.api.cine.model.Filme;
import com.lima.api.cine.model.Sala;
import com.lima.api.cine.model.Sessao;
import com.lima.api.cine.validator.ExistsUuid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AbrirSessaoRequest(

    @NotNull
    @Future
    LocalDateTime dataHoraInicio,

    @NotNull
    @Future
    LocalDateTime dataHoraFim,

    @UUID
    @NotBlank
    @ExistsUuid(domainClass = Filme.class, fieldName = "uuid")
    String uuidFilme,

    @NotBlank
    String nomeSala,

    @NotNull
    @Positive
    BigDecimal valor
) {

    public Sessao toModel(Filme filme){
        return new Sessao(dataHoraInicio, dataHoraFim, filme, new Sala(nomeSala), valor);
    }
}
