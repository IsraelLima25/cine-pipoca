package com.lima.api.cine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sessoes")
public class SessaoController {

    @GetMapping
    public void listartodas(){ }

    @GetMapping("/filme")
    public void buscarPorFilme(){ }

    @PostMapping
    public void reservarAssento(){ }
}
