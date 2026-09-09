package com.jcaa.udec.soldados.domain.core.valueobject;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoInvalidoException;
import java.util.Objects;

public record ComponenteMilitar(String valor) {
    private static final int LONGITUD_MINIMA = 2;

    public ComponenteMilitar {
        if (Objects.isNull(valor) || valor.isBlank() || valor.length() < LONGITUD_MINIMA) {
            throw new SoldadoInvalidoException();
        }
    }
}
