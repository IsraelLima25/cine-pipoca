package com.lima.api.cine.service;

import com.lima.api.cine.enun.FormaPagamento;
import com.lima.api.cine.model.Cliente;
import com.lima.api.cine.model.Ingresso;
import com.lima.api.cine.model.Sessao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IngressoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngressoService.class);

    private final AssentoService assentoService;

    public IngressoService(AssentoService assentoService) {
        this.assentoService = assentoService;
    }

    public void comprar(Cliente cliente, Sessao sessao, int numeroAssento, FormaPagamento formaPagamento){

        /**
         * 1 - Incluir cliente DB
         * 2 - Incluir como dados do cliente CPF campo unique
         * 2 - Buscar sessao DB
         * 3 - Tornar forma de pagamento uma enum e não uma tabela. Na enum pode ter um abstract para um contrato de pagamento que os tipos assinam
         * 4 - Reservar o assento
         * 5 - Criar forma de pagamento
         * 6 - Criar Ingresso
         * 7 - Pagar ingresso(call gateway passando a forma de pagamento)
         * 8 - Ocupar o assento
         */

        LOGGER.info("Reservando assento numero = {} para o cliente {}", numeroAssento, cliente.getNome());
        assentoService.reservarAssento(cliente, sessao, numeroAssento);
        LOGGER.info("Assento numero = {} reservado com sucesso para o cliente {}", numeroAssento, cliente.getNome());

        LOGGER.info("Gerando ingresso para o filme {} do cliente {}", sessao.getFilme().getTitulo(), cliente.getNome());
        Ingresso ingresso = new Ingresso(sessao, false);
        LOGGER.info("""
                
                INGRESSO - {}
                CLIENTE - {}
                SALA - {}
                ASSENTO - {}
                [GERADO COM SUCESSO]                
                """, sessao.getFilme().getTitulo(), cliente.getNome(), sessao.getSala().getNome(), numeroAssento);
        LOGGER.info("Iniciando comunicação com gateway de pagamento para ingresso do filme {} do cliente {}", sessao.getFilme().getTitulo(), cliente.getNome());
        ingresso.pagar(formaPagamento);
        LOGGER.info("Pagamento para ingresso do filme {} do cliente {} realizado com sucesso", sessao.getFilme().getTitulo(), cliente.getNome());

        LOGGER.info("Ocupando assento numero = {} para o cliente {}", numeroAssento, cliente.getNome());
        assentoService.ocuparAssento(cliente, sessao, numeroAssento);
        LOGGER.info("Assento numero = {} para o cliente {} ocupado com sucesso. Desejamos uma ótima sessão", numeroAssento, cliente.getNome());

    }
}
