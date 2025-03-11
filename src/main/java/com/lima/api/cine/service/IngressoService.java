package com.lima.api.cine.service;

import com.lima.api.cine.enums.FormaPagamento;
import com.lima.api.cine.model.Ingresso;
import com.lima.api.cine.model.Reserva;
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
    public Ingresso emitirIngresso(Reserva reserva, boolean meiaEntrada, FormaPagamento formaPagamento, int numeroAssento){
        LOGGER.info("Gerando ingresso para o filme {} do cliente {}", reserva.getSessao().getFilme().getTitulo());
        Ingresso ingresso = new Ingresso(meiaEntrada, formaPagamento, reserva.getSessao(), reserva);
        ingressoRepository.save(ingresso);
        LOGGER.info("""
                
                INGRESSO - {}                
                SALA - {}
                ASSENTO - {}
                [GERADO COM SUCESSO]                
                """, reserva.getSessao().getFilme().getTitulo(), reserva.getSessao().getSala().getNome(), numeroAssento);

        return ingresso;
    }

    // TODO fazer tratamento de excessões personalizado
    // TODO averiguar as transações distribuidas
    // TODO melhorar a bateria de testes buscando mais confiabilidade na descoberta de bugs

    @Transactional(rollbackFor = Exception.class)
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
