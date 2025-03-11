package com.lima.api.cine.controller.response;

import com.lima.api.cine.enums.StatusSala;

import java.util.List;

public record SalaResponse(
    String nome,
    StatusSala status,
    List<AssentoResponse> assentos
) {
}
