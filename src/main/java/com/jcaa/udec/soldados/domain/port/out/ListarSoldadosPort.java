package com.jcaa.udec.soldados.domain.port.out;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import java.util.List;

public interface ListarSoldadosPort {
    List<Soldado> obtenerTodos();
}
