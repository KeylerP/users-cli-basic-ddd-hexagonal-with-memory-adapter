package com.jcaa.udec.soldados.domain.core.exception;

public class SoldadoYaExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "El soldado ya existe.";

    public SoldadoYaExisteException() {
        super(MENSAJE_ERROR);
    }
}
