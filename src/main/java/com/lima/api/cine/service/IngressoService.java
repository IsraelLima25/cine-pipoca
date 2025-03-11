package com.lima.api.cine.service;

import com.lima.api.cine.enums.FormaPagamento;
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
    public Ingresso emitirIngresso(Sessao sessao, boolean meiaEntrada, FormaPagamento formaPagamento, int numeroAssento){
        LOGGER.info("Gerando ingresso para o filme {} do cliente {}", sessao.getFilme().getTitulo());
        Ingresso ingresso = new Ingresso(meiaEntrada, formaPagamento, sessao, null);
        ingressoRepository.save(ingresso);
        LOGGER.info("""
                
                INGRESSO - {}                
                SALA - {}
                ASSENTO - {}
                [GERADO COM SUCESSO]                
                """, sessao.getFilme().getTitulo(), sessao.getSala().getNome(), numeroAssento);

        return ingresso;
    }

    public String pagar(Ingresso ingresso) {

        LOGGER.info("Iniciando comunicação com gateway de pagamento para ingresso do filme {}",
                ingresso.getReserva().getSessao().getFilme().getTitulo());

        // TODO chamar gateway de pagamento usando CompletableFuture<?>
        String codigoPagamento = ingresso.pagar();
        LOGGER.info("Pagamento código = {} para ingresso do filme {}  realizado com sucesso.", codigoPagamento,
                ingresso.getReserva().getSessao().getFilme().getTitulo());

        LOGGER.info("Ocupando assento numero = {}", ingresso.getReserva().getAssento().getNumero());
        ingresso.getReserva().getAssento().confirmarReserva();
        LOGGER.info("Assento numero = {} ocupado com sucesso. Desejamos uma ótima sessão",
                ingresso.getReserva().getAssento().getNumero());

        return codigoPagamento;
    }
}
