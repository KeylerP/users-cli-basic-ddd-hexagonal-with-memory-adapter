package com.jcaa.udec.collections.domain.core.exception;

public class UsuarioYaExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "El usuario ya existe.";

    public UsuarioYaExisteException() {
        super(MENSAJE_ERROR);
    }
}
