package com.jcaa.udec.soldados.application.service.ports.in;

import com.jcaa.udec.soldados.application.service.dto.query.BuscarSoldadoConsulta;
import com.jcaa.udec.soldados.domain.core.model.Soldado;

public interface BuscarSoldadoUseCase {
    Soldado buscarPorId(BuscarSoldadoConsulta consulta);
}
