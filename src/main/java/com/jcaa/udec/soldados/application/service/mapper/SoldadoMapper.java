package com.jcaa.udec.soldados.application.service.mapper;

import com.jcaa.udec.soldados.application.service.dto.command.CrearSoldadoComando;
import com.jcaa.udec.soldados.domain.core.model.Soldado;

public final class SoldadoMapper {
    private SoldadoMapper() {
    }

    public static Soldado mapearASoldado(CrearSoldadoComando comando) {
        return Soldado.builder()
                .id(comando.id())
                .nombreCompleto(comando.nombreCompleto())
                .rango(comando.rango())
                .componenteMilitar(comando.componenteMilitar())
                .build();
    }
}
