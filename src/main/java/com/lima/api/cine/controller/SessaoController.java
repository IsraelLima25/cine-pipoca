package com.lima.api.cine.controller;

import com.lima.api.cine.controller.request.AbrirSessaoRequest;
import com.lima.api.cine.controller.request.ReservarSessaoRequest;
import com.lima.api.cine.controller.response.SessaoResponse;
import com.lima.api.cine.exception.BusinessException;
import com.lima.api.cine.model.Filme;
import com.lima.api.cine.model.Ingresso;
import com.lima.api.cine.model.Reserva;
import com.lima.api.cine.model.Sessao;
import com.lima.api.cine.repository.FilmeRepository;
import com.lima.api.cine.repository.SessaoRepository;
import com.lima.api.cine.service.IngressoService;
import com.lima.api.cine.service.SessaoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sessoes")
public class SessaoController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SessaoController.class);

    private final SessaoRepository sessaoRepository;
    private final FilmeRepository filmeRepository;
    private final SessaoService sessaoService;
    private final IngressoService ingressoService;

    public SessaoController(SessaoRepository sessaoRepository, FilmeRepository filmeRepository, SessaoService sessaoService, IngressoService ingressoService) {
        this.sessaoRepository = sessaoRepository;
        this.filmeRepository = filmeRepository;
        this.sessaoService = sessaoService;
        this.ingressoService = ingressoService;
    }

    @PostMapping("/abrir")
    public ResponseEntity<Void> abrir(@Valid @RequestBody AbrirSessaoRequest request){

        LOGGER.info("Iniciando abertura da sessao para o filmeId = {}", request.idFilme());
        // TODO customizar validador existsById filter validation hibernate
        Filme filme = filmeRepository.findById(request.idFilme())
                .orElseThrow(() -> {
                    LOGGER.error("Filme com id = {} não existe na base de dados", request.idFilme());
                    return  new BusinessException("Filme não existe! Não foi possível abrir uma sessão");
                });
        Sessao sessao = request.toModel(filme);
        sessaoRepository.save(sessao);

        LOGGER.info("Sessão para o filme = {} aberta. Inicio = {} Fim = {}", sessao.getFilme().getTitulo(),
                sessao.getDataHoraInicio(), sessao.getDataHoraFim());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sessao.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/filme/{id}")
    public ResponseEntity<List<SessaoResponse>> buscarPorFilme(@PathVariable("id") Long id){

        LOGGER.info("Verificando existencia do filme = {}", id);
        // TODO customizar validador existsById filter validation hibernate
        filmeRepository.findById(id)
                .orElseThrow(() -> {
                   LOGGER.error("Filme com id = {} não existe na base de dados", id);
                   return  new BusinessException("Filme não existe! Não foi possível abrir uma sessão");
                });

        LOGGER.info("Filme com id = {} encontrado com sucesso", id);

        List<Sessao> sessoes = sessaoRepository.listarSessoesDisponiveisPoridFilme(id);
        List<SessaoResponse> listSessaoModel = sessoes.stream().map(Sessao::toRepresentacaoView).toList();

        LOGGER.info("Lista de sessões disponiveis para o filme com id = {} retornada com sucesso", id);
        return ResponseEntity.ok(listSessaoModel);
    }

    @PostMapping("/reservar")
    public ResponseEntity<Ingresso> reservar(@Valid @RequestBody ReservarSessaoRequest request){

        LOGGER.info("Inicando reserva de assento na sessao");
        // TODO customizar validador existsById filter validation hibernate
        Sessao sessao = sessaoRepository.findById(request.idSessao())
                .orElseThrow(() -> {
                    LOGGER.error("Sessão com id = {} não existe na base de dados", request.idSessao());
                    return  new BusinessException("Sessão não existe! Não foi possível fazer uma reserva");
                });

        Reserva reserva = sessaoService.reservarAssento(sessao, request.numeroAssento());
        LOGGER.info("Reserva realizada com sucesso para a sessao = {} e assento = {} ", request.idSessao(), request.numeroAssento());

        LOGGER.info("Iniciando emissão de ingressso para a sessao id = {} e assento = {}", request.idSessao(), request.numeroAssento());
        Ingresso ingressoEmitido = ingressoService
                .emitirIngresso(reserva, request.isMeiaEntrada(), request.formaPagamento(), request.numeroAssento());

        // TODO retornar uma representação e não o model!!!

        return ResponseEntity.ok(ingressoEmitido);
    }
}
