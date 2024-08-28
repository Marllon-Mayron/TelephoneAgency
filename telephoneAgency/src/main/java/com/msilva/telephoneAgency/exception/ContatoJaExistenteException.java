package com.msilva.telephoneAgency.exception;

public class ContatoJaExistenteException extends RuntimeException {
    public ContatoJaExistenteException(String message) {
        super(message);
    }
}