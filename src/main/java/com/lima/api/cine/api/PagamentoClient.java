package com.lima.api.cine.api;

import com.lima.api.cine.api.request.PagamentoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "pagamento-client", url = "http://localhost:8100/api/v1/pagamentos")
public interface PagamentoClient {

    @PostMapping("/pix")
    ResponseEntity<String>pagar(@RequestBody PagamentoRequest pagamentoRequest);
}
