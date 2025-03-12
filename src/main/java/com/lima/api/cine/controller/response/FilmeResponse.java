package com.lima.api.cine.controller.response;

import com.lima.api.cine.enums.TipoIdiomaFilme;

public record FilmeResponse(
    String uuid,
    String titulo,
    TipoIdiomaFilme tipoIdioma,
    String duracao
) { }
