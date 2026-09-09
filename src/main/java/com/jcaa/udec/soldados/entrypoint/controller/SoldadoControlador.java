package com.jcaa.udec.soldados.entrypoint.controller;

import com.jcaa.udec.soldados.entrypoint.controller.dto.request.ActualizarSoldadoPeticion;
import com.jcaa.udec.soldados.entrypoint.controller.dto.request.RegistrarSoldadoPeticion;
import com.jcaa.udec.soldados.entrypoint.controller.dto.response.ObtenerSoldadoResponse;

public interface SoldadoControlador {
    void registrar(RegistrarSoldadoPeticion peticion);

    ObtenerSoldadoResponse obtenerPorId(String id);

    ObtenerSoldadoResponse obtenerTodos();

    void actualizar(ActualizarSoldadoPeticion peticion);
}
