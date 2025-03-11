package com.lima.api.cine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Sessao sessao;

    @ManyToOne
    private Assento assento;

    public Reserva(Sessao sessao, Assento assento) {
        this.sessao = sessao;
        this.assento = assento;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Assento getAssento() {
        return assento;
    }
}
