package com.jcaa.udec.soldados.application.service;

import com.jcaa.udec.soldados.application.service.dto.command.ActualizarSoldadoComando;
import com.jcaa.udec.soldados.application.service.mapper.SoldadoMapper;
import com.jcaa.udec.soldados.application.service.ports.in.ActualizarSoldadoUseCase;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.port.out.ActualizarSoldadoPort;

public class ActualizarSoldadoService implements ActualizarSoldadoUseCase {
    private final ActualizarSoldadoPort actualizarSoldadoPort;

    public ActualizarSoldadoService(ActualizarSoldadoPort actualizarSoldadoPort) {
        this.actualizarSoldadoPort = actualizarSoldadoPort;
    }

    @Override
    public void actualizar(ActualizarSoldadoComando comando) {
        Soldado soldado = SoldadoMapper.mapearASoldado(comando);
        actualizarSoldadoPort.actualizar(soldado);
    }
}
