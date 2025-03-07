package com.lima.api.cine.model;

import com.lima.api.cine.enun.StatusAssento;

public class Assento {

    private int numero;
    private StatusAssento status;

    public Assento(int numero){
        this.numero = numero;
        this.status = StatusAssento.VAZIO;
    }

    public void reservar(){
        this.status = StatusAssento.RESERVADO;
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
                '}';
    }
}
