package com.lima.api.cine.model;

import com.lima.api.cine.enums.StatusSala;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_sala")
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "sala")
    private List<Assento> assentos = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusSala status;

    @Deprecated
    public Sala(){}

    public Sala(String nome){
        this.nome = nome;
        this.status = StatusSala.FECHADA;
        criarAssentos(10);
    }

    public List<Assento> getAssentos() {
        return assentos;
    }

    public String getNome() {
        return nome;
    }

    public void abrirSala(){
        this.status = StatusSala.ABERTA;
    }

    public void fecharSala(){
        this.status = StatusSala.FECHADA;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "nome='" + nome + '\'' +
                ", assentos=" + assentos +
                ", status=" + status +
                '}';
    }

    private void criarAssentos(int quantidadeAssentos) {
        for(int i = 1; i <= quantidadeAssentos; i++){
            this.assentos.add(new Assento(i, this));
        }
    }

}
