package com.jcaa.udec.soldados.domain.core.exception;

public class SoldadoInvalidoException extends RuntimeException {
    private static final String MENSAJE_ERROR = "Los datos del soldado son invalidos.";

    public SoldadoInvalidoException() {
        super(MENSAJE_ERROR);
    }
}
