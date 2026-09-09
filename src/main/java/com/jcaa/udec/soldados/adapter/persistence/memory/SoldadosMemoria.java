package com.jcaa.udec.soldados.adapter.persistence.memory;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import java.util.ArrayList;
import java.util.List;

final class SoldadosMemoria {
    private static final List<Soldado> SOLDADOS = new ArrayList<>();

    private SoldadosMemoria() {
    }

    static List<Soldado> obtenerSoldados() {
        return SOLDADOS;
    }
}
