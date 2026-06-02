package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import java.util.Objects;

public record NombreUsuario(String valor) {
    private static final int LONGITUD_MINIMA = 3;

    public NombreUsuario {
        if (Objects.isNull(valor) || valor.isBlank() || valor.length() < LONGITUD_MINIMA) {
            throw new UsuarioInvalidoException();
        }
    }
}
