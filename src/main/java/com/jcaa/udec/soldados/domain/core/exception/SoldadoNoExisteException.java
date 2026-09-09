package com.jcaa.udec.soldados.domain.core.exception;

public class SoldadoNoExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "El soldado no existe.";

    public SoldadoNoExisteException() {
        super(MENSAJE_ERROR);
    }
}
