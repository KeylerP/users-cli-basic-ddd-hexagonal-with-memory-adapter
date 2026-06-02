package com.jcaa.udec.collections.entrypoint.controller.dto.response;

import java.util.List;

public record ObtenerUsuarioResponse(List<UsuarioResponse> usuarios) {
    public ObtenerUsuarioResponse {
        usuarios = List.copyOf(usuarios);
    }

    public boolean estaVacia() {
        return usuarios.isEmpty();
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), usuarios.stream()
                .map(UsuarioResponse::toString)
                .toList());
    }
}
