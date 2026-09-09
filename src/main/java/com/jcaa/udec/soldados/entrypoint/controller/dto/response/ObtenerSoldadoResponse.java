package com.jcaa.udec.soldados.entrypoint.controller.dto.response;

import java.util.List;

public record ObtenerSoldadoResponse(List<SoldadoResponse> soldados) {
    public ObtenerSoldadoResponse {
        soldados = List.copyOf(soldados);
    }

    public boolean estaVacia() {
        return soldados.isEmpty();
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), soldados.stream()
                .map(SoldadoResponse::toString)
                .toList());
    }
}
