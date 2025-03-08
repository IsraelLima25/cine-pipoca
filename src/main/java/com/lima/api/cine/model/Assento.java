package com.lima.api.cine.model;

import com.lima.api.cine.enun.StatusAssento;
import com.lima.api.cine.exception.AssentoIndisponivelException;
import jakarta.persistence.*;

@Entity
@Table(name = "tbl_assento")
public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private int numero;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusAssento status;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @ManyToOne
    private Cliente cliente;

    @Deprecated
    public Assento(){}

    public Assento(int numero, Sala sala){
        this.sala = sala;
        this.numero = numero;
        this.status = StatusAssento.VAZIO;
    }

    public synchronized void reservar(Cliente cliente){

        if(status != StatusAssento.VAZIO){
            throw new AssentoIndisponivelException("Este assento já foi reservado");
        }

        System.out.println(Thread.currentThread().getName() + " passou na verificação do assento vazio...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.cliente = cliente;
        this.status = StatusAssento.RESERVADO;

        System.out.println(Thread.currentThread().getName() + " reservou o assento para " + cliente.getNome());
    }

    public void ocupar(Cliente cliente) {
        if(status != StatusAssento.RESERVADO){
            throw new AssentoIndisponivelException("Este assento já foi ocupado");
        }
        this.cliente = cliente;
        this.status = StatusAssento.OCUPADO;
    }

    public int getNumero() {
        return numero;
    }

    public StatusAssento getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Assento{" +
                "numero=" + numero +
                ", status=" + status +
                ", cliente=" + cliente.getNome() +
                '}';
    }
}
