package com.jcaa.udec.collections.entrypoint.controller.dto.response;

import lombok.Builder;

@Builder
public record UsuarioResponse(String id, String nombre, String email) {
    private static final String FORMATO_DATOS = """
            ID: %s
            PASSWORD: ****
            NOMBRE: %s
            EMAIL: %s
            """;

    @Override
    public String toString() {
        return FORMATO_DATOS.formatted(id, nombre, email);
    }
}
