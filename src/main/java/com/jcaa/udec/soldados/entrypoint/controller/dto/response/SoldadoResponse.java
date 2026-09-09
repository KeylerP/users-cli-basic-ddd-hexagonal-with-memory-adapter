package com.jcaa.udec.soldados.entrypoint.controller.dto.response;

public record SoldadoResponse(String id, String nombreCompleto, String rango, String componenteMilitar) {
    private static final String FORMATO_DATOS = """
            ID: %s
            NOMBRE: %s
            RANGO: %s
            COMPONENTE: %s
            """;

    @Override
    public String toString() {
        return FORMATO_DATOS.formatted(id, nombreCompleto, rango, componenteMilitar);
    }
}
