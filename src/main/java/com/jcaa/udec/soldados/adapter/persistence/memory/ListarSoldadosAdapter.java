package com.jcaa.udec.soldados.adapter.persistence.memory;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.port.out.ListarSoldadosPort;
import java.util.List;

public class ListarSoldadosAdapter implements ListarSoldadosPort {
    private final List<Soldado> soldados = SoldadosMemoria.obtenerSoldados();

    @Override
    public List<Soldado> obtenerTodos() {
        return List.copyOf(soldados);
    }
}
