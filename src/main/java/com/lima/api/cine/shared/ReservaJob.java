package com.lima.api.cine.shared;

import com.lima.api.cine.enums.StatusValidade;
import com.lima.api.cine.exception.BusinessException;
import com.lima.api.cine.model.Ingresso;
import com.lima.api.cine.repository.IngressoRepository;
import com.lima.api.cine.service.SessaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ReservaJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservaJob.class);

    private final IngressoRepository ingressoRepository;
    private final SessaoService sessaoService;

    public ReservaJob(IngressoRepository ingressoRepository, SessaoService sessaoService) {
        this.ingressoRepository = ingressoRepository;
        this.sessaoService = sessaoService;
    }

    @Transactional(rollbackFor = Exception.class)
    @Scheduled(fixedRate = 5 * 60 * 1000) // A cada 5 minutos
    public void liberarReservasExpiradas(){
        try {
            LOGGER.info("Iniciando execução do job para cancelar reservas de ingressos vencidos");
            List<Ingresso> ingressos = ingressoRepository.listarIngressosNaoExpirados(StatusValidade.NAO_EXPIRADO);
            ingressos.forEach(ingresso -> {
                if (ingresso.isDataExpirada()) {
                    ingresso.expirar();
                    LOGGER.info("Ingresso do cliente {} expirado com sucesso", ingresso.getCliente());

                    ingresso.getSessao().getSala().getAssentos().stream()
                            .filter(assento -> assento.getCliente().getCpf().equals(ingresso.getCliente().getCpf()))
                            .forEach(assento -> {
                                LOGGER.info("Assento numero = {} do cliente {} para a sessao hora-inicio = {} do filme = {} foi liberado",
                                        assento.getNumero(), assento.getCliente().getNome(), ingresso.getSessao().getDataHoraInicio(),
                                        ingresso.getSessao().getFilme().getTitulo());
                                sessaoService.cancelarReservaAssento(ingresso.getSessao(), assento.getNumero(), ingresso.getCliente());
                            });
                }
            });
            LOGGER.info("Job para cancelar reservas de ingressos vencidos executado com sucesso");
        }catch (Exception ex){
            LOGGER.error("Erro ao executar job para cancelar reservas de ingressos vencidos", ex.getMessage());
            throw new BusinessException("Erro ao executar job para cancelar reservas de ingressos vencidos");
        }
    }
}
