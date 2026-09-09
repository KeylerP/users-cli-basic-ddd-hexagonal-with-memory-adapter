package com.jcaa.udec.soldados.adapter.persistence.memory;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoNoExisteException;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.port.out.EliminarSoldadoPort;
import java.util.List;
import java.util.Objects;

public class EliminarSoldadoAdapter implements EliminarSoldadoPort {
    private final List<Soldado> soldados = SoldadosMemoria.obtenerSoldados();

    @Override
    public void eliminar(String id) {
        for (int indice = 0; indice < soldados.size(); indice++) {
            if (Objects.equals(soldados.get(indice).getId(), id)) {
                soldados.remove(indice);
                return;
            }
        }
        throw new SoldadoNoExisteException();
    }
}
