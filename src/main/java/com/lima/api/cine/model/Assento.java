package com.lima.api.cine.model;

import com.lima.api.cine.enun.StatusAssento;

public class Assento {

    private int numero;
    private StatusAssento status;
    private Cliente cliente;

    public Assento(int numero){
        this.numero = numero;
        this.status = StatusAssento.VAZIO;
        this.cliente = new Cliente("DESCONHECIDO");
    }

    public void reservar(Cliente cliente){
        this.cliente = cliente;
        this.status = StatusAssento.RESERVADO;
    }

    public void ocupar(Cliente cliente) {
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
