package com.jcaa.udec.soldados.adapter.persistence.memory;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoYaExisteException;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.port.out.GuardarSoldadoPort;
import java.util.List;
import java.util.Objects;

public class GuardarSoldadoAdapter implements GuardarSoldadoPort {
    private final List<Soldado> soldados = SoldadosMemoria.obtenerSoldados();

    @Override
    public void guardar(Soldado soldado) {
        for (Soldado soldadoRegistrado : soldados) {
            if (Objects.equals(soldadoRegistrado.getId(), soldado.getId())) {
                throw new SoldadoYaExisteException();
            }
        }
        soldados.add(soldado);
    }
}
