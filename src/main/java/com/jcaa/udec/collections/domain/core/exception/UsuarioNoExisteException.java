package com.jcaa.udec.collections.domain.core.exception;

public class UsuarioNoExisteException extends RuntimeException {
    private static final String MENSAJE_ERROR = "El usuario no existe.";

    public UsuarioNoExisteException() {
        super(MENSAJE_ERROR);
    }
}
