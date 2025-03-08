package com.lima.api.cine.model;

import com.lima.api.cine.enun.FormaPagamento;
import com.lima.api.cine.enun.IdiomaFilme;
import com.lima.api.cine.service.IngressoService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MainModel {

    public static void main(String[] args) {

        /**
         * 1 - Subir um Database Docker Mysql - OK
         * 2 - Conectar a API ao DataBase - OK
         * 3 - Criar as entidades/relacionamentos usando flyway - OK
         * 4 - Criar as anotações JPA - OK
         * 5 - Criar os controllers/Services/Entities Sring Ecosystem
         * 6 - Aplicar Lock Otimista para a reserva de poltronas
         * 7 - Criar testes automatizados adicionando confiabilidade ao código
         */


        /**
         * Inicio fluxo
         */

        IngressoService ingressoService = new IngressoService();

        /**
         * Criar sessão
         */

        Filme filme = new Filme("O auto da compadecida 2", IdiomaFilme.DUBLADO, "2h");
        Sala sala = new Sala("A");
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataHoraInicio = agora.withHour(16).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime dataHoraFim = dataHoraInicio.plus(2, ChronoUnit.HOURS);
        Sessao sessao = new Sessao(dataHoraInicio, dataHoraFim, filme, sala, new BigDecimal("40.00"));

        /**
         * Criar cliente
         */
        Cliente cliente1 = new Cliente("João");
        Cliente cliente2 = new Cliente("Maria");

        /**
         * Executa serviço de compra
         */

        Thread threadJoao = new Thread(() -> ingressoService.comprar(cliente1, sessao, 1, FormaPagamento.PIX));
        Thread threadMaria = new Thread(() -> ingressoService.comprar(cliente2, sessao, 1, FormaPagamento.CREDITO));

        threadJoao.start();
        threadMaria.start();

        //ingressoService.comprar(cliente1, sessao, 1, FormaPagamento.PIX);
        //ingressoService.comprar(cliente2, sessao, 1, FormaPagamento.CREDITO);

        try {
            threadJoao.join();
            threadMaria.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(sessao);

        /**
         * Finaliza fluxo
         */
    }
}
