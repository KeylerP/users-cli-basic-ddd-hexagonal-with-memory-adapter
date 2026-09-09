package com.jcaa.udec.soldados.application.service.ports.in;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import java.util.List;

public interface ListarSoldadosUseCase {
    List<Soldado> obtenerTodos();
}
