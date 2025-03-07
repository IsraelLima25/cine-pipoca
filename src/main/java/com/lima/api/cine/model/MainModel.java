package com.lima.api.cine.model;

import com.lima.api.cine.enun.FormaPagamento;
import com.lima.api.cine.enun.IdiomaFilme;
import com.lima.api.cine.service.IngressoService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MainModel {

    public static void main(String[] args) {

        IngressoService ingressoService = new IngressoService();

        List<Sessao> sessaoList = new ArrayList<>();

        Filme filme = new Filme("O auto da compadecida 2", IdiomaFilme.DUBLADO, "2h");
        Sala sala = new Sala("A");
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataHoraInicio = agora.withHour(16).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime dataHoraFim = dataHoraInicio.plus(2, ChronoUnit.HOURS);
        Sessao sessao = new Sessao(dataHoraInicio, dataHoraFim, filme, sala, new BigDecimal("40.00"));

        sessaoList.add(sessao);

        Cliente cliente = new Cliente("Jonh");

        Sessao sessao1 = sessaoList.get(0);
        ingressoService.comprar(cliente, sessao, 1, FormaPagamento.PIX);

        System.out.println(sessao);
    }
}
