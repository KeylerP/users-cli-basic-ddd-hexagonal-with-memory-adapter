package com.jcaa.udec.soldados.domain.core.valueobject;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoInvalidoException;
import java.util.Objects;

public record NombreCompleto(String valor) {
    private static final int LONGITUD_MINIMA = 3;

    public NombreCompleto {
        if (Objects.isNull(valor) || valor.isBlank() || valor.length() < LONGITUD_MINIMA) {
            throw new SoldadoInvalidoException();
        }
    }
}
