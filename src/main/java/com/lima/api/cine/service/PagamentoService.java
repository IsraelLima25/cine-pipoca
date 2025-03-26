package com.lima.api.cine.service;

import com.lima.api.cine.api.PagamentoClient;
import com.lima.api.cine.api.request.PagamentoRequest;
import com.lima.api.cine.exception.InfrastructureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PagamentoService.class);

    private final PagamentoClient pagamentoClient;

    public PagamentoService(PagamentoClient pagamentoClient) {
        this.pagamentoClient = pagamentoClient;
    }

    // TODO: Fault tolerance, resilient (Retry, Fallback ....)
    public String pagar(PagamentoRequest pagamentoRequest) {

        try{
            LOGGER.info("Iniciando comunicação com o serviço de pagamento");
            ResponseEntity<String> pagamentoResponse = pagamentoClient.pagar(pagamentoRequest);
            var result = pagamentoResponse.getBody();
            LOGGER.info("Pagamento confirmado com sucesso, id gerado -> {}", result);
            return result;
        }catch (RuntimeException ex){
            var mensagemErro = "Erro ao conectar com serviço de pagamento";
            LOGGER.error("{}: Detalhe -> {} ", mensagemErro, ex.getMessage() );
            throw new InfrastructureException("Erro ao conectar com serviço de pagamento");
        }
    }
}
