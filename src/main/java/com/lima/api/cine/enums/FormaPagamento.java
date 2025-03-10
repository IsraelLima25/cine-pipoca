package com.lima.api.cine.enums;

import java.math.BigDecimal;

// TODO usar CompletableFuture<T> para comunicação com gateway de pagamento

public enum FormaPagamento {

    DEBITO{
        @Override
        public void executar(BigDecimal valor) {
            System.out.println("Iniciando pagamento no débito");
        }
    },
    CREDITO {
        @Override
        public void executar(BigDecimal valor) {
            System.out.println("Iniciando pagamento no crédito");
        }
    },
    PIX {
        @Override
        public void executar(BigDecimal valor) {
            System.out.println("Iniciando pagamento no pix");
        }
    };

    public abstract void executar(BigDecimal valor);
}
