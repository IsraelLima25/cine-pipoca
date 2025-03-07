package com.lima.api.cine.model;

import com.lima.api.cine.enun.IdiomaFilme;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MainModel {

    public static void main(String[] args) {

        Filme filme = new Filme("O auto da compadecida 2", IdiomaFilme.DUBLADO, "2h");
        Sala sala = new Sala("A");
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataHoraInicio = agora.withHour(16).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime dataHoraFim = dataHoraInicio.plus(2, ChronoUnit.HOURS);
        Sessao sessao = new Sessao(dataHoraInicio, dataHoraFim, filme, sala, new BigDecimal("40.00"));

        System.out.println(sessao);

    }
}
