package com.jcaa.udec.soldados.application.service;

import com.jcaa.udec.soldados.application.service.ports.in.ListarSoldadosUseCase;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.port.out.ListarSoldadosPort;
import java.util.List;

public class ListarSoldadosService implements ListarSoldadosUseCase {
    private final ListarSoldadosPort listarSoldadosPort;

    public ListarSoldadosService(ListarSoldadosPort listarSoldadosPort) {
        this.listarSoldadosPort = listarSoldadosPort;
    }

    @Override
    public List<Soldado> obtenerTodos() {
        return listarSoldadosPort.obtenerTodos();
    }
}
