package com.lima.api.cine.model;

import com.lima.api.cine.controller.response.SessaoResponse;
import com.lima.api.cine.enums.StatusSessao;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_sessao")
public class Sessao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusSessao status;

    @Column(name = "data_hora_inicio")
    private LocalDateTime dataHoraInicio;

    @Column(name = "data_hora_fim")
    private LocalDateTime dataHoraFim;

    @ManyToOne
    private Filme filme;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "sala_sessao_id")
    private Sala sala;

    @Column(name = "valor")
    private BigDecimal valor;

    @Deprecated
    public Sessao(){ }

    public Sessao(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim, Filme filme, Sala sala, BigDecimal valor) {
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraFim = dataHoraFim;
        this.filme = filme;
        this.sala = sala;
        this.valor = valor;
        this.abrirSessao();
        this.uuid = UUID.randomUUID().toString();
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

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }

    public LocalDateTime getDataHoraFim() {
        return dataHoraFim;
    }

    // essa mistura de ingês com português está ficando ótima rsrs
    public SessaoResponse toRepresentacaoView(){

        return new SessaoResponse(uuid, status, dataHoraInicio, dataHoraFim,
                filme.toRepresentacaoView(),
                sala.toRepresentacaoView(),
                valor);
    }

    public void abrirSessao(){
        this.status = StatusSessao.DISPONIVEL;
        this.sala.abrirSala();
    }

    public void fecharSessao(){
        this.status = StatusSessao.ESGOTADA;
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
