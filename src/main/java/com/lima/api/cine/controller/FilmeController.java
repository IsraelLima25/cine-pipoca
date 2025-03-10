package com.lima.api.cine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/filmes")
public class FilmeController {

    @GetMapping
    public void listarTodos(){ }
}
