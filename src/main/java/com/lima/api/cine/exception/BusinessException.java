package com.lima.api.cine.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String mensagem){
        super(mensagem);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
