package com.lima.api.cine.controller.response;

import com.lima.api.cine.enums.StatusAssento;

public record AssentoResponse(
    StatusAssento status,
    int numero
) { }
