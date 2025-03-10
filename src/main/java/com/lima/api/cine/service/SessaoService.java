package com.lima.api.cine.service;

import com.lima.api.cine.enums.FormaPagamento;
import com.lima.api.cine.exception.AssentoIndisponivelException;
import com.lima.api.cine.exception.BusinessException;
import com.lima.api.cine.model.Cliente;
import com.lima.api.cine.model.Ingresso;
import com.lima.api.cine.model.Sessao;
import com.lima.api.cine.repository.IngressoRepository;
import com.lima.api.cine.repository.SessaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SessaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoService.class);

    private final ClienteService clienteService;
    private final SessaoRepository sessaoRepository;
    private final IngressoService ingressoService;

    public SessaoService(ClienteService clienteService, SessaoRepository sessaoRepository, IngressoService ingressoService) {
        this.clienteService = clienteService;
        this.sessaoRepository = sessaoRepository;
        this.ingressoService = ingressoService;
    }

    @Transactional(rollbackFor = Exception.class)
    public Ingresso reservarAssento(Cliente cliente, Sessao sessao, int numeroAssento, FormaPagamento formaPagamento, boolean meiaEntrada){

        try{
            LOGGER.info("Iniciando reserva assento numero = {} para o cliente {}", numeroAssento, cliente.getNome());
            Cliente clienteModel = clienteService.buscarOuSalvar(cliente);
            Sessao sessaoModel = sessaoRepository.findById(sessao.getId())
                    .orElseThrow(() -> {
                        LOGGER.error("Sessão não encontrada para o ID: {}", sessao.getId());
                        return new BusinessException("Sessão não encontrada para o ID: " + sessao.getId());
                    });

            LOGGER.info("Reservando assento numero = {} para o cliente {}", numeroAssento, cliente.getNome());
            sessao.getSala().getAssentos().stream()
                    .filter(assento -> assento.getNumero() == numeroAssento)
                    .findFirst().get().reservar(cliente);

            LOGGER.info("Assento numero = {} reservado com sucesso para o cliente {}", numeroAssento, cliente.getNome());

            return ingressoService.emitirIngresso(sessao, cliente, meiaEntrada, formaPagamento, numeroAssento);
        }catch (AssentoIndisponivelException assentoIndisponivelException){
            LOGGER.error("Assento indisponivel");
            throw assentoIndisponivelException;
        }catch (Exception ex){
            LOGGER.error("Erro ao reservar assento");
            throw new BusinessException("Erro ao reservar assento");
        }
    }
    public void cancelarReservaAssento(Sessao sessao, int numeroAssento, Cliente cliente){

        try{
            LOGGER.info("Iniciando cancelamento da reserva do assento numero = {} para o cliente {}", numeroAssento, cliente.getNome());
            sessao.getSala().getAssentos().stream()
                    .filter(assento -> assento.getNumero() == numeroAssento)
                    .findFirst().get().cancelarReserva();
            LOGGER.info("Cancelamento da reserva do assento numero = {} para o cliente {} reservado com sucesso", numeroAssento, cliente.getNome());
        }catch (BusinessException ex){
            LOGGER.error("Não podemos cancelar porque o pagamento já foi realizado, não aceitamos devolução");
            throw ex;
        }
    }

    private void confirmarReserva(Cliente cliente, Sessao sessao, int numeroAssento){
        sessao.getSala().getAssentos().stream()
                .filter(assento -> assento.getNumero() == numeroAssento)
                .findFirst().get().confirmarReserva(cliente);
    }
}
