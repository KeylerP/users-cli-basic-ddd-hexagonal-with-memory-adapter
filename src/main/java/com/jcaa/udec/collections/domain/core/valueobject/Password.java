package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import java.util.Objects;

public record Password(String valor) {
    private static final int LONGITUD_MINIMA = 10;

    public Password {
        if (!esValido(valor)) {
            throw new UsuarioInvalidoException();
        }
    }

    private static boolean esValido(String valor) {
        if (Objects.isNull(valor) || valor.length() < LONGITUD_MINIMA) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneSimbolo = false;
        for (int indice = 0; indice < valor.length(); indice++) {
            char caracter = valor.charAt(indice);
            tieneMayuscula |= Character.isUpperCase(caracter);
            tieneMinuscula |= Character.isLowerCase(caracter);
            tieneNumero |= Character.isDigit(caracter);
            tieneSimbolo |= !Character.isLetterOrDigit(caracter);
        }
        return tieneMayuscula && tieneMinuscula && tieneNumero && tieneSimbolo;
    }
}
