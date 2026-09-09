package com.jcaa.udec.soldados.entrypoint.controller;

import com.jcaa.udec.soldados.application.service.dto.command.CrearSoldadoComando;
import com.jcaa.udec.soldados.application.service.ports.in.CrearSoldadoUseCase;
import com.jcaa.udec.soldados.entrypoint.controller.dto.request.RegistrarSoldadoPeticion;

public class SoldadoControladorImpl implements SoldadoControlador {
    private final CrearSoldadoUseCase crearSoldadoUseCase;

    public SoldadoControladorImpl(CrearSoldadoUseCase crearSoldadoUseCase) {
        this.crearSoldadoUseCase = crearSoldadoUseCase;
    }

    @Override
    public void registrar(RegistrarSoldadoPeticion peticion) {
        CrearSoldadoComando comando = new CrearSoldadoComando(
                peticion.id(),
                peticion.nombreCompleto(),
                peticion.rango(),
                peticion.componenteMilitar());
        crearSoldadoUseCase.guardar(comando);
    }
}
