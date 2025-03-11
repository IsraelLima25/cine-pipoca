package com.lima.api.cine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sessao_reserva")
    private Sessao sessao;

    @ManyToOne
    @JoinColumn(name = "id_assento_reserva")
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
