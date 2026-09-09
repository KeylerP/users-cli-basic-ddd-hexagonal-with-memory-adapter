package com.jcaa.udec.soldados.application.service;

import com.jcaa.udec.soldados.application.service.dto.query.BuscarSoldadoConsulta;
import com.jcaa.udec.soldados.application.service.ports.in.BuscarSoldadoUseCase;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.port.out.BuscarSoldadoPort;

public class BuscarSoldadoService implements BuscarSoldadoUseCase {
    private final BuscarSoldadoPort buscarSoldadoPort;

    public BuscarSoldadoService(BuscarSoldadoPort buscarSoldadoPort) {
        this.buscarSoldadoPort = buscarSoldadoPort;
    }

    @Override
    public Soldado buscarPorId(BuscarSoldadoConsulta consulta) {
        return buscarSoldadoPort.buscarPorId(consulta.id());
    }
}
