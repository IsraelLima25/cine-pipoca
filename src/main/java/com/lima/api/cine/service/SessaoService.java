package com.lima.api.cine.service;

import com.lima.api.cine.exception.AssentoIndisponivelException;
import com.lima.api.cine.exception.BusinessException;
import com.lima.api.cine.exception.InfrastructureException;
import com.lima.api.cine.model.Assento;
import com.lima.api.cine.model.Reserva;
import com.lima.api.cine.model.Sessao;
import com.lima.api.cine.repository.ReservaRepository;
import com.lima.api.cine.repository.SessaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SessaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessaoService.class);

    private final SessaoRepository sessaoRepository;
    private final ReservaRepository reservaRepository;

    public SessaoService(SessaoRepository sessaoRepository, ReservaRepository reservaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.reservaRepository = reservaRepository;
    }

    public Reserva reservarAssento(Sessao sessao, int numeroAssento){

        try{

            LOGGER.info("Reservando assento numero = {} para o cliente {}", numeroAssento);
            Assento assentoReserva = sessao.getSala().getAssentos().stream()
                    .filter(assento -> assento.getNumero() == numeroAssento)
                    .findFirst().orElseThrow(() -> new AssentoIndisponivelException("Assento com número " + numeroAssento + " não encontrado na sala."));

            // TODO: alterar para assento.reservar()!!! Melhora a legibilidade do código
            assentoReserva.reservar();

            Reserva reserva = new Reserva(sessao, assentoReserva);
            reservaRepository.save(reserva);

            LOGGER.info("Assento numero = {} reservado com sucesso para o cliente {}", numeroAssento);
            return reserva;

        }catch (AssentoIndisponivelException assentoIndisponivelException){
            LOGGER.error("Assento indisponivel");
            throw assentoIndisponivelException;
        }catch (Exception ex){
            LOGGER.error("Erro ao reservar assento", ex);
            throw new InfrastructureException("Erro ao reservar assento");
        }
    }
    public void cancelarReservaAssento(Sessao sessao, int numeroAssento){

        try{
            LOGGER.info("Iniciando cancelamento da reserva do assento numero = {} para o cliente {}", numeroAssento);
            sessao.getSala().getAssentos().stream()
                    .filter(assento -> assento.getNumero() == numeroAssento)
                    .findFirst().get().cancelarReserva();
            LOGGER.info("Cancelamento da reserva do assento numero = {} para o cliente {} reservado com sucesso", numeroAssento);
        }catch (BusinessException ex){
            LOGGER.error("Não podemos cancelar porque o pagamento já foi realizado, não aceitamos devolução");
            throw ex;
        }
    }
}
