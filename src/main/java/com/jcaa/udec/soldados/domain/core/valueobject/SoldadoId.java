package com.jcaa.udec.soldados.domain.core.valueobject;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoInvalidoException;
import java.util.Objects;

public record SoldadoId(String valor) {
    private static final int LONGITUD_MINIMA = 6;

    public SoldadoId {
        if (Objects.isNull(valor) || valor.isBlank() || !esNumeroValido(valor)) {
            throw new SoldadoInvalidoException();
        }
    }

    private static boolean esNumeroValido(String valor) {
        if (valor.length() < LONGITUD_MINIMA) {
            return false;
        }
        try {
            Long.parseLong(valor);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
}
