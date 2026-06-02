package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import java.util.Objects;
import lombok.Builder;

public class Usuario {
    private static final int LONGITUD_MINIMA_NOMBRE = 3;
    private static final int LONGITUD_MINIMA_PASSWORD = 10;
    private static final int LONGITUD_MINIMA_USUARIO_EMAIL = 2;
    private static final int LONGITUD_MINIMA_DOMINIO_EMAIL = 5;
    private static final String ARROBA = "@";
    private static final String PUNTO = ".";
    private static final String ESPACIO = " ";
    private final String id;
    private final String nombre;
    private final String email;

    @Builder
    public Usuario(String id, String password, String nombre, String email) {
        if (!esIdValido(id)
                || !esPasswordValido(password)
                || !esNombreValido(nombre)
                || !esEmailValido(email)) {
            throw new UsuarioInvalidoException();
        }
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    private static boolean esIdValido(String id) {
        if (esTextoVacio(id)) {
            return false;
        }
        try {
            Integer.parseInt(id);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private static boolean esPasswordValido(String password) {
        if (Objects.isNull(password) || password.length() < LONGITUD_MINIMA_PASSWORD) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneSimbolo = false;
        for (int indice = 0; indice < password.length(); indice++) {
            char caracter = password.charAt(indice);
            tieneMayuscula |= Character.isUpperCase(caracter);
            tieneMinuscula |= Character.isLowerCase(caracter);
            tieneNumero |= Character.isDigit(caracter);
            tieneSimbolo |= !Character.isLetterOrDigit(caracter);
        }
        return tieneMayuscula && tieneMinuscula && tieneNumero && tieneSimbolo;
    }

    private static boolean esNombreValido(String nombre) {
        return !esTextoVacio(nombre) && nombre.length() >= LONGITUD_MINIMA_NOMBRE;
    }

    private static boolean esEmailValido(String email) {
        if (esTextoVacio(email) || email.contains(ESPACIO)) {
            return false;
        }

        int posicionArroba = email.indexOf(ARROBA);
        int ultimaPosicionArroba = email.lastIndexOf(ARROBA);
        if (posicionArroba <= 0
                || posicionArroba != ultimaPosicionArroba
                || posicionArroba == email.length() - 1) {
            return false;
        }

        String usuario = email.substring(0, posicionArroba);
        String dominio = email.substring(posicionArroba + 1);
        return usuario.length() >= LONGITUD_MINIMA_USUARIO_EMAIL
                && dominio.length() >= LONGITUD_MINIMA_DOMINIO_EMAIL
                && dominio.contains(PUNTO)
                && esSeccionEmailValida(usuario)
                && esSeccionEmailValida(dominio);
    }

    private static boolean esSeccionEmailValida(String seccion) {
        for (int indice = 0; indice < seccion.length(); indice++) {
            char caracter = seccion.charAt(indice);
            if (!Character.isLetterOrDigit(caracter) && caracter != '.' && caracter != '_') {
                return false;
            }
        }
        return true;
    }

    private static boolean esTextoVacio(String texto) {
        return Objects.isNull(texto) || texto.isBlank();
    }

}
