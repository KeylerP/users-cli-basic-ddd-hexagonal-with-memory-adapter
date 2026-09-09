package com.jcaa.udec.soldados.domain.port.out;

import com.jcaa.udec.soldados.domain.core.model.Soldado;

public interface BuscarSoldadoPort {
    Soldado buscarPorId(String id);
}
