package com.lima.api.cine.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cpf", unique = true, length = 11)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Deprecated
    public Cliente(){}

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }
}
