package com.lima.api.cine.service;

import com.lima.api.cine.model.Cliente;
import com.lima.api.cine.model.Sessao;
import org.springframework.stereotype.Service;

@Service
public class AssentoService {

    public void reservarAssento(Cliente cliente, Sessao sessao, int numeroAssento) {
        sessao.getSala().getAssentos().stream()
                .filter(assento -> assento.getNumero() == numeroAssento)
                .findFirst().get().reservar(cliente);
    }

    public void ocuparAssento(Cliente cliente, Sessao sessao, int numeroAssento){
        sessao.getSala().getAssentos().stream()
                .filter(assento -> assento.getNumero() == numeroAssento)
                .findFirst().get().ocupar(cliente);
    }
}
