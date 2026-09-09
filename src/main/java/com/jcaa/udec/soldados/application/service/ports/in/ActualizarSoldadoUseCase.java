package com.jcaa.udec.soldados.application.service.ports.in;

import com.jcaa.udec.soldados.application.service.dto.command.ActualizarSoldadoComando;

public interface ActualizarSoldadoUseCase {
    void actualizar(ActualizarSoldadoComando comando);
}
