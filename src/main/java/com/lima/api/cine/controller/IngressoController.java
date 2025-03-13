package com.lima.api.cine.controller;

import com.lima.api.cine.controller.response.PagamentoResponse;
import com.lima.api.cine.exception.RecursoNaoEncontradoException;
import com.lima.api.cine.model.Ingresso;
import com.lima.api.cine.repository.IngressoRepository;
import com.lima.api.cine.service.IngressoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/api/v1/ingressos")
public class IngressoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngressoController.class);

    private final IngressoRepository ingressoRepository;
    private final IngressoService ingressoService;

    public IngressoController(IngressoRepository ingressoRepository, IngressoService ingressoService) {
        this.ingressoRepository = ingressoRepository;
        this.ingressoService = ingressoService;
    }

    // TODO receber uma lista de ingressos e pagar todos de uma só vez
    @PostMapping("/pagar/{uuid}")
    public ResponseEntity<PagamentoResponse> pagar(@PathVariable("uuid") UUID uuid){


        Ingresso ingresso = ingressoRepository.findByUuid(uuid.toString())
                .orElseThrow(() -> {
                    LOGGER.error("Filme com id = {} não existe na base de dados", uuid);
                    return new RecursoNaoEncontradoException("Ingresso não existe! Não foi possível fazer pagamento");
                });
        return ResponseEntity.ok(new PagamentoResponse(ingressoService.pagar(ingresso)));
    }
}
