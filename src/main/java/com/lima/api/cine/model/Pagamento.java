package com.lima.api.cine.model;

import com.lima.api.cine.enun.FormaPagamento;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;

    @Deprecated
    public Pagamento(){}

    public Pagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
}
