package com.jcaa.udec.soldados.entrypoint.controller;

import com.jcaa.udec.soldados.entrypoint.controller.dto.request.RegistrarSoldadoPeticion;

public interface SoldadoControlador {
    void registrar(RegistrarSoldadoPeticion peticion);
}
