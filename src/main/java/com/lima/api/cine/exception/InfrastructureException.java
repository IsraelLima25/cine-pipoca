package com.lima.api.cine.exception;

public class InfrastructureException extends RuntimeException {

    public InfrastructureException(String mensagem){
        super(mensagem);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
