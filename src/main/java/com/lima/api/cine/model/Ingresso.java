package com.lima.api.cine.model;

import com.lima.api.cine.enun.FormaPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;

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

    @ManyToOne
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id_ingresso")
    private Cliente cliente;

    @ManyToOne
    private Sessao sessao;

    @Deprecated
    public Ingresso(){}

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
