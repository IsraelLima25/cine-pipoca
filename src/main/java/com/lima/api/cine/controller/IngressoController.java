package com.lima.api.cine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ingressos")
public class IngressoController {

    @GetMapping
    public void listarPorCpf(){ }

    @PostMapping
    public void pagar(){ }
}
