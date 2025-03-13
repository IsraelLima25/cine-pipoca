package com.lima.api.cine.controller;

import com.lima.api.cine.controller.request.AbrirSessaoRequest;
import com.lima.api.cine.controller.request.ReservarSessaoRequest;
import com.lima.api.cine.controller.response.ReservaIngressoResponse;
import com.lima.api.cine.controller.response.SessaoResponse;
import com.lima.api.cine.exception.BusinessException;
import com.lima.api.cine.exception.RecursoNaoEncontradoException;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

        LOGGER.info("Iniciando abertura da sessao para o filmeId = {}", request.uuidFilme());
        Filme filme = filmeRepository.findByUuid(request.uuidFilme()).get();
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

    @GetMapping("/filme/{uuid}")
    public ResponseEntity<List<SessaoResponse>> buscarPorFilme(@PathVariable("uuid") UUID uuid){

        LOGGER.info("Verificando existencia do filme = {}", uuid.toString());
        filmeRepository.findByUuid(uuid.toString())
                .orElseThrow(() -> {
                   LOGGER.error("Filme com id = {} não existe na base de dados", uuid);
                   return  new RecursoNaoEncontradoException("Filme não existe! Não foi possível abrir uma sessão");
                });

        LOGGER.info("Filme com id = {} encontrado com sucesso", uuid);

        List<Sessao> sessoes = sessaoRepository.listarSessoesDisponiveisPorUuidFilme(uuid.toString());
        List<SessaoResponse> listSessaoModel = sessoes.stream().map(Sessao::toRepresentacaoView).toList();

        LOGGER.info("Lista de sessões disponiveis para o filme com id = {} retornada com sucesso", uuid);
        return ResponseEntity.ok(listSessaoModel);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/reservar")
    public ResponseEntity<ReservaIngressoResponse> reservar(@Valid @RequestBody ReservarSessaoRequest request){

        LOGGER.info("Inicando reserva de assento na sessao");
        Sessao sessao = sessaoRepository.findByUuid(request.uuidSessao()).get();

        Reserva reserva = sessaoService.reservarAssento(sessao, request.numeroAssento());
        LOGGER.info("Reserva realizada com sucesso para a sessao = {} e assento = {} ", request.uuidSessao(), request.numeroAssento());

        LOGGER.info("Iniciando emissão de ingressso para a sessao id = {} e assento = {}", request.uuidSessao(), request.numeroAssento());
        Ingresso ingressoEmitido = ingressoService
                .emitirIngresso(reserva, request.isMeiaEntrada(), request.formaPagamento(), request.numeroAssento());

        ReservaIngressoResponse reservaIngressoResponse = ingressoEmitido.toRepresentacaoView();

        return ResponseEntity.ok(reservaIngressoResponse);
    }
}
