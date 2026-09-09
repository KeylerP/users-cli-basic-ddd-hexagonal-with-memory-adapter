package com.jcaa.udec.soldados.application.service.ports.in;

import com.jcaa.udec.soldados.application.service.dto.command.EliminarSoldadoComando;

public interface EliminarSoldadoUseCase {
    void eliminar(EliminarSoldadoComando comando);
}
