package com.lima.api.cine.model;

import com.lima.api.cine.enums.FormaPagamento;
import com.lima.api.cine.enums.StatusPagamento;
import com.lima.api.cine.enums.StatusValidade;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_ingresso")
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meia_entrada")
    private boolean isMeiaEntrada;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id_ingresso")
    private Cliente cliente;

    @ManyToOne
    private Sessao sessao;

    @Enumerated(EnumType.STRING)
    private StatusValidade statusValidade;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Column(name = "expira_em")
    private LocalDateTime expiraEm;

    @Deprecated
    public Ingresso(){}

    public Ingresso(boolean isMeiaEntrada, FormaPagamento formaPagamento, Cliente cliente, Sessao sessao) {
        this.isMeiaEntrada = isMeiaEntrada;
        this.formaPagamento = formaPagamento;
        this.cliente = cliente;
        this.sessao = sessao;
        this.expiraEm = LocalDateTime.now().plusMinutes(15);
        calcularValorTotal();
        this.statusValidade = StatusValidade.NAO_EXPIRADO;
        this.statusPagamento = StatusPagamento.AGUARDANDO;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public boolean isMeiaEntrada() {
        return isMeiaEntrada;
    }

    public boolean isDataExpirada(){
        return LocalDateTime.now().isAfter(this.expiraEm);
    }

    public void expirar(){
        this.statusValidade = StatusValidade.EXPIRADO;
    }

    public void pagar(){
        this.formaPagamento.executar(valorTotal);
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    private void calcularValorTotal() {
        this.valorTotal = isMeiaEntrada ?
                sessao.getValor().divide(new BigDecimal("2")) : sessao.getValor();
    }
}
