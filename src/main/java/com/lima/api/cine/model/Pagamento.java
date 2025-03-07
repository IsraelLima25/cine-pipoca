package com.lima.api.cine.model;

import com.lima.api.cine.enun.FormaPagamento;

public class Pagamento {

    private FormaPagamento formaPagamento;

    public Pagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
}
