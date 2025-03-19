package com.lima.api.cine.model;

import com.lima.api.cine.enums.FormaPagamento;
import com.lima.api.cine.enums.TipoIdiomaFilme;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class IngressoTest {


    @Test
    @DisplayName("Quando um ingresso é meia entrada o valor total deve ser a metade do preço")
    void test1(){

        //cenário
        Sessao sessao = criarSessaoCenario();
        Reserva reserva = new Reserva(sessao, new Assento(10, new Sala("B")));

        //execução
        Ingresso ingresso = new Ingresso(true, FormaPagamento.PIX, reserva);

        //verificação
        assertEquals(new BigDecimal("20.00"), ingresso.getValorTotal());
        assertTrue(ingresso.isMeiaEntrada());
        assertNotEquals(new BigDecimal("19.99"), ingresso.getValorTotal());
        assertNotEquals(new BigDecimal("20.01"), ingresso.getValorTotal());
    }

    @Test
    @DisplayName("Quando um ingresso não é meia entrada o valor total não deve ser a metade do preço")
    void test2(){

        //cenário
        Sessao sessao = criarSessaoCenario();
        Reserva reserva = new Reserva(sessao, new Assento(10, new Sala("B")));

        //execução
        Ingresso ingresso = new Ingresso(false, FormaPagamento.PIX, reserva);

        //verificação
        assertEquals(new BigDecimal("40.00"), ingresso.getValorTotal());
        assertFalse(ingresso.isMeiaEntrada());
    }

    @Test
    @DisplayName("Quando um ingresso está expirado após 15min e 01seg deve retorna true")
    void test3(){

        //cenário
        Clock clock = Clock.fixed(LocalDateTime.now()
                .plusMinutes(15)
                .plusSeconds(01)
                .atZone(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault());

        LocalDateTime nowWithClock = LocalDateTime.now(clock);

        Sessao sessao = criarSessaoCenario();
        Reserva reserva = new Reserva(sessao, new Assento(10, new Sala("B")));
        Ingresso ingresso = new Ingresso(false, FormaPagamento.PIX, reserva);

        //execução
        assertTrue(ingresso.isDataExpirada(nowWithClock));
    }

    @Test
    @DisplayName("Quando um ingresso não está expirado com 14min e 59seg deve retorna false")
    void test4(){

        //cenário
        Clock clock = Clock.fixed(LocalDateTime.now()
                        .plusMinutes(14)
                        .plusSeconds(59)
                        .atZone(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault());

        LocalDateTime nowWithClock = LocalDateTime.now(clock);

        Sessao sessao = criarSessaoCenario();
        Reserva reserva = new Reserva(sessao, new Assento(10, new Sala("B")));
        Ingresso ingresso = new Ingresso(false, FormaPagamento.PIX, reserva);

        //execução
        assertFalse(ingresso.isDataExpirada(nowWithClock));
    }

    private Sessao criarSessaoCenario(){

        Filme filme = new Filme("O auto da compadecida 2", TipoIdiomaFilme.DUBLADO, "2h");
        Sala sala = new Sala("A");
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime dataHoraInicio = agora.withHour(16).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime dataHoraFim = dataHoraInicio.plus(2, ChronoUnit.HOURS);
        return new Sessao(dataHoraInicio, dataHoraFim, filme, sala, new BigDecimal("40.00"));
    }
}