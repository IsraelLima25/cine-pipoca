package com.lima.api.cine.controller;

import com.lima.api.cine.controller.response.FilmeResponse;
import com.lima.api.cine.model.Filme;
import com.lima.api.cine.repository.FilmeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filmes")
public class FilmeController {

    private final FilmeRepository filmeRepository;

    public FilmeController(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @GetMapping
    public ResponseEntity<List<FilmeResponse>> listarTodos(){

        List<Filme> todosFilmes = filmeRepository.findAll();
        List<FilmeResponse> filmesResponse = todosFilmes.
                stream()
                .map(Filme::toRepresentacaoView)
                .toList();

        return ResponseEntity.ok(filmesResponse);
    }
}
