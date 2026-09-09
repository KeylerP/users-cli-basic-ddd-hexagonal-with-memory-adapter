package com.jcaa.udec.soldados.adapter.persistence.memory;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoNoExisteException;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.port.out.BuscarSoldadoPort;
import java.util.List;
import java.util.Objects;

public class BuscarSoldadoAdapter implements BuscarSoldadoPort {
    private final List<Soldado> soldados = SoldadosMemoria.obtenerSoldados();

    @Override
    public Soldado buscarPorId(String id) {
        for (Soldado soldado : soldados) {
            if (Objects.equals(soldado.getId(), id)) {
                return soldado;
            }
        }
        throw new SoldadoNoExisteException();
    }
}
