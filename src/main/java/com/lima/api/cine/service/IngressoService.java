package com.lima.api.cine.service;

import com.lima.api.cine.enun.FormaPagamento;
import com.lima.api.cine.model.Cliente;
import com.lima.api.cine.model.Ingresso;
import com.lima.api.cine.model.Sessao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IngressoService {

    Logger log = LoggerFactory.getLogger(IngressoService.class);

    public void comprar(Cliente cliente, Sessao sessao, int numeroAssento, FormaPagamento formaPagamento){

        log.info("Reservando assento numero = {} para o cliente {}", numeroAssento, cliente.getNome());
        reservarAssento(cliente, sessao, numeroAssento);
        log.info("Assento numero = {} reservado com sucesso para o cliente {}", numeroAssento, cliente.getNome());

        log.info("Gerando ingresso para o filme {} do cliente {}", sessao.getFilme().getTitulo(), cliente.getNome());
        Ingresso ingresso = new Ingresso(sessao, false);
        log.info("""
                
                INGRESSO - {}
                CLIENTE - {}
                SALA - {}
                ASSENTO - {}
                [GERADO COM SUCESSO]                
                """, sessao.getFilme().getTitulo(), cliente.getNome(), sessao.getSala().getNome(), numeroAssento);
        log.info("Iniciando comunicação com gateway de pagamento para ingresso do filme {} do cliente {}", sessao.getFilme().getTitulo(), cliente.getNome());
        ingresso.pagar(formaPagamento);
        log.info("Pagamento para ingresso do filme {} do cliente {} realizado com sucesso", sessao.getFilme().getTitulo(), cliente.getNome());

        log.info("Ocupando assento numero = {} para o cliente {}", numeroAssento, cliente.getNome());
        ocuparAssento(cliente, sessao, numeroAssento);
        log.info("Assento numero = {} para o cliente {} ocupado com sucesso. Desejamos uma ótima sessão", numeroAssento, cliente.getNome());

    }

    // TODO segregar em um service de assentos
    private void reservarAssento(Cliente cliente, Sessao sessao, int numeroAssento) {
        sessao.getSala().getAssentos().stream()
                .filter(assento -> assento.getNumero() == numeroAssento)
                .findFirst().get().reservar(cliente);
    }

    // TODO segregar em um service de assentos
    private void ocuparAssento(Cliente cliente, Sessao sessao, int numeroAssento){
        sessao.getSala().getAssentos().stream()
                .filter(assento -> assento.getNumero() == numeroAssento)
                .findFirst().get().ocupar(cliente);
    }
}
