package com.lima.api.cine.exception;

public class AssentoIndisponivelException extends RuntimeException{

    public AssentoIndisponivelException(String mensagem){
        super(mensagem);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
