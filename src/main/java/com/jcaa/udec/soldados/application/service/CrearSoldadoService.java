package com.jcaa.udec.soldados.application.service;

import com.jcaa.udec.soldados.application.service.dto.command.CrearSoldadoComando;
import com.jcaa.udec.soldados.application.service.mapper.SoldadoMapper;
import com.jcaa.udec.soldados.application.service.ports.in.CrearSoldadoUseCase;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.port.out.GuardarSoldadoPort;

public class CrearSoldadoService implements CrearSoldadoUseCase {
    private final GuardarSoldadoPort guardarSoldadoPort;

    public CrearSoldadoService(GuardarSoldadoPort guardarSoldadoPort) {
        this.guardarSoldadoPort = guardarSoldadoPort;
    }

    @Override
    public void guardar(CrearSoldadoComando comando) {
        Soldado soldado = SoldadoMapper.mapearASoldado(comando);
        guardarSoldadoPort.guardar(soldado);
    }
}
