package com.lima.api.cine.model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "tbl_reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = "id_sessao_reserva")
    private Sessao sessao;

    @ManyToOne
    @JoinColumn(name = "id_assento_reserva")
    private Assento assento;

    @Deprecated
    public Reserva(){ }

    public Reserva(Sessao sessao, Assento assento) {
        this.sessao = sessao;
        this.assento = assento;
        this.uuid = UUID.randomUUID().toString();
    }

    public Sessao getSessao() {
        return sessao;
    }

    public Assento getAssento() {
        return assento;
    }
}
