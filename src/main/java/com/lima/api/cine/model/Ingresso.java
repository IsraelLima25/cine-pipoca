package com.lima.api.cine.model;

import com.lima.api.cine.enun.FormaPagamento;

import java.math.BigDecimal;

public class Ingresso {

    private Sessao sessao;
    private boolean isMeiaEntrada;
    private BigDecimal valorTotal;
    private Pagamento pagamento;
    private Cliente cliente;

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

    public void pagar(FormaPagamento formaPagamento){
        this.pagamento = new Pagamento(formaPagamento);
    }

    private void calcularValorTotal() {
        this.valorTotal = isMeiaEntrada ?
                sessao.getValor().divide(new BigDecimal("2")) : sessao.getValor();
    }
}
