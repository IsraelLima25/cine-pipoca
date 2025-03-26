package com.lima.api.cine.service;

import com.lima.api.cine.api.request.PagamentoRequest;
import com.lima.api.cine.enums.FormaPagamento;
import com.lima.api.cine.exception.BusinessException;
import com.lima.api.cine.exception.InfrastructureException;
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
    private final PagamentoService pagamentoService;

    public IngressoService(IngressoRepository ingressoRepository, PagamentoService pagamentoService) {
        this.ingressoRepository = ingressoRepository;
        this.pagamentoService = pagamentoService;
    }

    public Ingresso emitirIngresso(Reserva reserva, boolean meiaEntrada, FormaPagamento formaPagamento, int numeroAssento){

        try{
            LOGGER.info("Gerando ingresso para o filme {} do cliente {}", reserva.getSessao().getFilme().getTitulo());
            Ingresso ingresso = new Ingresso(meiaEntrada, formaPagamento, reserva);
            ingressoRepository.save(ingresso);
            LOGGER.info("""
                
                INGRESSO - {}                
                SALA - {}
                ASSENTO - {}
                [GERADO COM SUCESSO]                
                """, reserva.getSessao().getFilme().getTitulo(), reserva.getSessao().getSala().getNome(), numeroAssento);

            return ingresso;
        }catch (Exception ex){
            LOGGER.error("Erro emitir ingresso", ex);
            throw new InfrastructureException("Um erro aconteceu! Não foi possível emitir o ingresso.");
        }
    }

    // TODO melhorar a bateria de testes buscando mais confiabilidade na descoberta de bugs
    @Transactional(rollbackFor = Exception.class)
    public String pagar(Ingresso ingresso) {

        try{
            LOGGER.info("Iniciando validação do ingresso {}", ingresso.getUuid());
            validarIngresso(ingresso);
            LOGGER.info("Ingresso {} válido", ingresso.getUuid());

            LOGGER.info("Iniciando comunicação com gateway de pagamento para ingresso do filme {}",
                    ingresso.getReserva().getSessao().getFilme().getTitulo());
            String codigoPagamento = pagamentoService.pagar(new PagamentoRequest(ingresso.getUuid(), ingresso.getFormaPagamento(), ingresso.getValorTotal()));
            LOGGER.info("Pagamento código = {} para ingresso do filme {}  realizado com sucesso.", codigoPagamento,
                    ingresso.getReserva().getSessao().getFilme().getTitulo());

            LOGGER.info("Ocupando assento numero = {}", ingresso.getReserva().getAssento().getNumero());
            ingresso.getReserva().getAssento().confirmarReserva();
            LOGGER.info("Assento numero = {} ocupado com sucesso. Desejamos uma ótima sessão",
                    ingresso.getReserva().getAssento().getNumero());

            return codigoPagamento;
        }catch (RuntimeException ex){
            LOGGER.error("Erro ao processar pagamento do ingresso {}", ingresso.getUuid());
            throw ex;
        }
    }

    public boolean validarIngresso(Ingresso ingresso){
        try {
            LOGGER.info("Iniciando a validação do ingresso {}", ingresso.getUuid());
            ingresso.hasValid();
            LOGGER.info("Ingresso {} validado com sucesso", ingresso.getUuid());
            return true;
        }catch (BusinessException ex){
            LOGGER.error("Ingresso {} inválido", ingresso.getUuid());
            throw ex;
        }
    }
}
