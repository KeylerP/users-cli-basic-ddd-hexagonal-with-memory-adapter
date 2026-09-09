package com.jcaa.udec.soldados.entrypoint.controller.mapper;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.entrypoint.controller.dto.response.ObtenerSoldadoResponse;
import com.jcaa.udec.soldados.entrypoint.controller.dto.response.SoldadoResponse;
import java.util.List;

public final class SoldadoResponseMapper {
    private SoldadoResponseMapper() {
    }

    public static ObtenerSoldadoResponse mapearAResponse(Soldado soldado) {
        return new ObtenerSoldadoResponse(List.of(mapearAResponseSoldado(soldado)));
    }

    public static ObtenerSoldadoResponse mapearAResponse(List<Soldado> soldados) {
        return new ObtenerSoldadoResponse(soldados.stream()
                .map(SoldadoResponseMapper::mapearAResponseSoldado)
                .toList());
    }

    private static SoldadoResponse mapearAResponseSoldado(Soldado soldado) {
        return new SoldadoResponse(
                soldado.getId(),
                soldado.getNombreCompleto(),
                soldado.getRango(),
                soldado.getComponenteMilitar());
    }
}
