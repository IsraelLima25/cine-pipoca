package com.lima.api.cine.controller.response;

import com.lima.api.cine.enums.TipoIdiomaFilme;

public record FilmeResponse(
    Long id,
    String titulo,
    TipoIdiomaFilme tipoIdioma,
    String duracao
) { }
