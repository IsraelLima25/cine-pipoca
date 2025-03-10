package com.lima.api.cine.model;

import com.lima.api.cine.enums.FormaPagamento;
import com.lima.api.cine.enums.TipoIdiomaFilme;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.*;

class IngressoTest {


    @Test
    @DisplayName("Quando um ingresso é meia entrada o valor total deve ser a metade do preço")
    void test1(){

        //cenário
        Sessao sessao = criarSessaoCenario();

        //execução
        Ingresso ingresso = new Ingresso(true, FormaPagamento.PIX, new Cliente("Joao"), sessao);

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

        //execução
        Ingresso ingresso = new Ingresso(false, FormaPagamento.PIX, new Cliente("Joao"), sessao);

        //verificação
        assertEquals(new BigDecimal("40.00"), ingresso.getValorTotal());
        assertFalse(ingresso.isMeiaEntrada());
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