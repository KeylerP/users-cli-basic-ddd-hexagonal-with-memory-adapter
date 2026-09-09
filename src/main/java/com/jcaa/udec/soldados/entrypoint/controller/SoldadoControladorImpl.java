package com.jcaa.udec.soldados.entrypoint.controller;

import com.jcaa.udec.soldados.application.service.dto.command.CrearSoldadoComando;
import com.jcaa.udec.soldados.application.service.dto.query.BuscarSoldadoConsulta;
import com.jcaa.udec.soldados.application.service.ports.in.BuscarSoldadoUseCase;
import com.jcaa.udec.soldados.application.service.ports.in.CrearSoldadoUseCase;
import com.jcaa.udec.soldados.entrypoint.controller.dto.request.RegistrarSoldadoPeticion;
import com.jcaa.udec.soldados.entrypoint.controller.dto.response.ObtenerSoldadoResponse;
import com.jcaa.udec.soldados.entrypoint.controller.mapper.SoldadoResponseMapper;

public class SoldadoControladorImpl implements SoldadoControlador {
    private final CrearSoldadoUseCase crearSoldadoUseCase;
    private final BuscarSoldadoUseCase buscarSoldadoUseCase;

    public SoldadoControladorImpl(
            CrearSoldadoUseCase crearSoldadoUseCase,
            BuscarSoldadoUseCase buscarSoldadoUseCase) {
        this.crearSoldadoUseCase = crearSoldadoUseCase;
        this.buscarSoldadoUseCase = buscarSoldadoUseCase;
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

    @Override
    public ObtenerSoldadoResponse obtenerPorId(String id) {
        BuscarSoldadoConsulta consulta = new BuscarSoldadoConsulta(id);
        return SoldadoResponseMapper.mapearAResponse(buscarSoldadoUseCase.buscarPorId(consulta));
    }
}
