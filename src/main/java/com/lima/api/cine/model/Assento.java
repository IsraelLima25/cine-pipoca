package com.lima.api.cine.model;

import com.lima.api.cine.controller.response.AssentoResponse;
import com.lima.api.cine.enums.StatusAssento;
import com.lima.api.cine.exception.AssentoIndisponivelException;
import com.lima.api.cine.exception.BusinessException;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tbl_assento")
public class Assento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private int numero;

    @Column(name = "uuid")
    private String uuid;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusAssento status;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @Deprecated
    public Assento(){}

    public Assento(int numero, Sala sala){
        this.uuid = UUID.randomUUID().toString();
        this.sala = sala;
        this.numero = numero;
        this.status = StatusAssento.VAZIO;
    }

    // condição de corrida
    public void reservar(){

        if(status != StatusAssento.VAZIO){
            throw new AssentoIndisponivelException("Este assento já foi reservado!");
        }

        this.status = StatusAssento.RESERVADO;
    }

    public void confirmarReserva() {
        if(status != StatusAssento.RESERVADO){
            throw new AssentoIndisponivelException("Este assento já foi ocupado!");
        }
        this.status = StatusAssento.OCUPADO;
    }

    public void cancelarReserva(){
        if(this.status == StatusAssento.OCUPADO){
            throw new BusinessException("Não podemos cancelar porque o pagamento já foi realizado, não aceitamos devolução");
        }
        this.status = StatusAssento.VAZIO;
    }

    public int getNumero() {
        return numero;
    }

    public StatusAssento getStatus() {
        return status;
    }

    public AssentoResponse toRepresentacaoView(){
        return new AssentoResponse(status, numero);
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Assento{" +
                "numero=" + numero +
                ", status=" + status +
                '}';
    }
}
