package com.lima.api.cine.model;

import com.lima.api.cine.enun.StatusSessao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_sessao")
public class Sessao {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private StatusSessao status;

    @Column(name = "data_hora_inicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim")
    private LocalDateTime dataHoraFim;

    @ManyToOne
    private Filme filme;

    @OneToOne
    private Sala sala;

    @Column(name = "valor")
    private BigDecimal valor;

    @Deprecated
    public Sessao(){}

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
