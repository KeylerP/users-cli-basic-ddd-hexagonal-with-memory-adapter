package com.jcaa.udec.soldados.application.service.ports.in;

import com.jcaa.udec.soldados.application.service.dto.command.CrearSoldadoComando;

public interface CrearSoldadoUseCase {
    void guardar(CrearSoldadoComando comando);
}
