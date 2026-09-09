package com.jcaa.udec.soldados.adapter.persistence.memory;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoNoExisteException;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.port.out.ActualizarSoldadoPort;
import java.util.List;
import java.util.Objects;

public class ActualizarSoldadoAdapter implements ActualizarSoldadoPort {
    private final List<Soldado> soldados = SoldadosMemoria.obtenerSoldados();

    @Override
    public void actualizar(Soldado soldado) {
        for (int indice = 0; indice < soldados.size(); indice++) {
            if (Objects.equals(soldados.get(indice).getId(), soldado.getId())) {
                soldados.set(indice, soldado);
                return;
            }
        }
        throw new SoldadoNoExisteException();
    }
}
