package com.lima.api.cine.service;

import com.lima.api.cine.enums.FormaPagamento;
import com.lima.api.cine.model.Cliente;
import com.lima.api.cine.model.Ingresso;
import com.lima.api.cine.model.Sessao;
import com.lima.api.cine.repository.IngressoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngressoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IngressoService.class);

    private final IngressoRepository ingressoRepository;

    public IngressoService(IngressoRepository ingressoRepository) {
        this.ingressoRepository = ingressoRepository;
    }

    @Transactional
    public Ingresso emitirIngresso(Sessao sessao, Cliente cliente, boolean meiaEntrada, FormaPagamento formaPagamento, int numeroAssento){
        LOGGER.info("Gerando ingresso para o filme {} do cliente {}", sessao.getFilme().getTitulo(), cliente.getNome());
        Ingresso ingresso = new Ingresso(meiaEntrada, formaPagamento, cliente, sessao);
        ingressoRepository.save(ingresso);
        LOGGER.info("""
                
                INGRESSO - {}
                CLIENTE - {}
                SALA - {}
                ASSENTO - {}
                [GERADO COM SUCESSO]                
                """, sessao.getFilme().getTitulo(), cliente.getNome(), sessao.getSala().getNome(), numeroAssento);

        return ingresso;
    }

    public void pagar(Long idIngresso) {



        /**
         * 7 - Pagar ingresso(call gateway passando a forma de pagamento)
         * 8 - Se o pagamento foi realizado com sucessp ocupar o assento caso contrario devolver o assento para o status Vazio
         * 9 - A entidade pagamento deve ter um status sobre o processamento do pagamento(PAGO, NÃO_PAGO...)
         */

        //ingresso.pagar();

        //LOGGER.info("Iniciando comunicação com gateway de pagamento para ingresso do filme {} do cliente {}", sessao.getFilme().getTitulo(), cliente.getNome());
        // ingresso.pagar(formaPagamento);
        //LOGGER.info("Pagamento para ingresso do filme {} do cliente {} realizado com sucesso", sessao.getFilme().getTitulo(), cliente.getNome());

        //LOGGER.info("Ocupando assento numero = {} para o cliente {}", numeroAssento, cliente.getNome());
        //assentoService.ocuparAssento(cliente, sessao, numeroAssento);
        //LOGGER.info("Assento numero = {} para o cliente {} ocupado com sucesso. Desejamos uma ótima sessão", numeroAssento, cliente.getNome());
        //return null;
    }
}
