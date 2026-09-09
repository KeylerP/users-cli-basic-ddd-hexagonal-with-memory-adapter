package com.jcaa.udec.soldados.application.service;

import com.jcaa.udec.soldados.application.service.dto.command.EliminarSoldadoComando;
import com.jcaa.udec.soldados.application.service.ports.in.EliminarSoldadoUseCase;
import com.jcaa.udec.soldados.domain.port.out.EliminarSoldadoPort;

public class EliminarSoldadoService implements EliminarSoldadoUseCase {
    private final EliminarSoldadoPort eliminarSoldadoPort;

    public EliminarSoldadoService(EliminarSoldadoPort eliminarSoldadoPort) {
        this.eliminarSoldadoPort = eliminarSoldadoPort;
    }

    @Override
    public void eliminar(EliminarSoldadoComando comando) {
        eliminarSoldadoPort.eliminar(comando.id());
    }
}
