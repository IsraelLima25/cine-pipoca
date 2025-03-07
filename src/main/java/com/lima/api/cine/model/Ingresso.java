package com.lima.api.cine.model;

import java.math.BigDecimal;

public class Ingresso {

    private Sessao sessao;
    private boolean isMeiaEntrada;
    private BigDecimal valorTotal;

    public Ingresso(Sessao sessao, boolean isMeiaEntrada) {
        this.sessao = sessao;
        this.isMeiaEntrada = isMeiaEntrada;
        calcularValorTotal();
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public boolean isMeiaEntrada() {
        return isMeiaEntrada;
    }

    private void calcularValorTotal() {
        this.valorTotal = isMeiaEntrada ?
                sessao.getValor().divide(new BigDecimal("2")) : sessao.getValor();
    }
}
