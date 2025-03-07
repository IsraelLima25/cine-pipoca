package com.lima.api.cine.service;

import com.lima.api.cine.enun.FormaPagamento;
import com.lima.api.cine.model.Ingresso;
import com.lima.api.cine.model.Sessao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IngressoService {

    Logger log = LoggerFactory.getLogger(IngressoService.class);

    public void comprar(Sessao sessao, int numeroAssento){

        log.info("Reservando assento numero = {} para compra", numeroAssento);
        reservarAssento(sessao, numeroAssento);
        log.info("Assento numero = {}, reservado com sucesso", numeroAssento);

        log.info("Gerando ingresso para o filme {}", sessao.getFilme().getTitulo());
        Ingresso ingresso = new Ingresso(sessao, false);
        log.info("""
                INGRESSO - {}
                [GERADO COM SUCESSO]
                """, sessao.getFilme().getTitulo());
        log.info("Iniciando comunicação com gateway de pagamento para ingresso do filme {}", sessao.getFilme().getTitulo());
        ingresso.pagar(FormaPagamento.PIX);
        log.info("Pagamento para ingresso do filme {} realizado com sucesso", sessao.getFilme().getTitulo());
    }

    private void reservarAssento(Sessao sessao, int numeroAssento) {
        sessao.getSala().getAssentos().stream()
                .filter(assento -> assento.getNumero() == numeroAssento)
                .findFirst().get().reservar();
    }
}
