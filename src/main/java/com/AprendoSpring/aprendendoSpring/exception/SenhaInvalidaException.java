package com.AprendoSpring.aprendendoSpring.exception;

public class SenhaInvalidaException extends RuntimeException {

    public SenhaInvalidaException(){
        super("Senha inválida");
    }

}
