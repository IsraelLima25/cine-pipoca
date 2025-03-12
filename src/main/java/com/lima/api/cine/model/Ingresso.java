package com.lima.api.cine.model;

import com.lima.api.cine.controller.response.ReservaIngressoResponse;
import com.lima.api.cine.enums.FormaPagamento;
import com.lima.api.cine.enums.StatusPagamento;
import com.lima.api.cine.enums.StatusValidade;
import com.lima.api.cine.exception.BusinessException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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
    private Reserva reserva;

    @Enumerated(EnumType.STRING)
    private StatusValidade statusValidade;

    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;

    @Column(name = "expira_em")
    private LocalDateTime expiraEm;

    @Deprecated
    public Ingresso(){}

    public Ingresso(boolean isMeiaEntrada, FormaPagamento formaPagamento, Sessao sessao, Reserva reserva) {
        this.isMeiaEntrada = isMeiaEntrada;
        this.formaPagamento = formaPagamento;
        this.reserva = reserva;
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

    public Reserva getReserva() {
        return reserva;
    }

    public boolean isDataExpirada(){
        return LocalDateTime.now().isAfter(this.expiraEm);
    }

    public void expirar(){
        this.statusValidade = StatusValidade.EXPIRADO;
    }

    public String pagar(){
        if(statusPagamento == StatusPagamento.PAGO || statusPagamento == StatusPagamento.CANCELADO){
            throw new BusinessException("Não foi possivel processar o pagamento. Este ingresso ou já foi pago ou está cancelado.");
        }
        if(statusValidade == StatusValidade.EXPIRADO){
            throw new BusinessException("Este ingresso expirou! Você perdeu o seu lugar na sessão. Favor emitir outro ingresso.");
        }
        this.formaPagamento.executar(valorTotal);
        this.statusPagamento = StatusPagamento.PAGO;

        return UUID.randomUUID().toString();
    }

    private void calcularValorTotal() {
        this.valorTotal = isMeiaEntrada ?
                reserva.getSessao().getValor().divide(new BigDecimal("2")) : reserva.getSessao().getValor();
    }

    public ReservaIngressoResponse toRepresentacaoView() {
        return new ReservaIngressoResponse(id, isMeiaEntrada, valorTotal, formaPagamento, expiraEm,
                statusValidade, statusPagamento, reserva.getSessao().getFilme().getTitulo(), reserva.getSessao().getSala().getNome(),
                reserva.getAssento().getNumero());
    }
}
