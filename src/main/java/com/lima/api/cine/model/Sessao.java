package com.lima.api.cine.model;

import com.lima.api.cine.enun.StatusSessao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Sessao {

    private StatusSessao status;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private Filme filme;
    private Sala sala;
    private BigDecimal valor;

    public Sessao(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, Filme filme, Sala sala, BigDecimal valor) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.filme = filme;
        this.sala = sala;
        this.valor = valor;
        this.abrirSessao();
    }

    public void abrirSessao(){
        this.status = StatusSessao.DISPONIVEL;
        this.sala.abrirSala();
    }

    public void fecharSessao(){
        this.status = StatusSessao.ESGOTADA;
    }

    public Sala getSala() {
        return sala;
    }

    public Filme getFilme() {
        return filme;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public StatusSessao getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "status=" + status +
                ", dataHoraInicio=" + dataHoraInicio +
                ", dataHoraFim=" + dataHoraFim +
                ", filme=" + filme +
                ", sala=" + sala +
                ", valor= R$" + valor +
                '}';
    }
}
