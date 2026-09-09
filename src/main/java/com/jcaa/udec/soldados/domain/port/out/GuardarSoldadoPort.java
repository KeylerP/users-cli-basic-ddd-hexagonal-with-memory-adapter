package com.jcaa.udec.soldados.domain.port.out;

import com.jcaa.udec.soldados.domain.core.model.Soldado;

public interface GuardarSoldadoPort {
    void guardar(Soldado soldado);
}
