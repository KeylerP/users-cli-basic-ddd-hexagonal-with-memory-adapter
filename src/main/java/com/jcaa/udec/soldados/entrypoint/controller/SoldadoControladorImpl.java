package com.jcaa.udec.soldados.entrypoint.controller;

import com.jcaa.udec.soldados.application.service.dto.command.ActualizarSoldadoComando;
import com.jcaa.udec.soldados.application.service.dto.command.CrearSoldadoComando;
import com.jcaa.udec.soldados.application.service.dto.query.BuscarSoldadoConsulta;
import com.jcaa.udec.soldados.application.service.ports.in.ActualizarSoldadoUseCase;
import com.jcaa.udec.soldados.application.service.ports.in.BuscarSoldadoUseCase;
import com.jcaa.udec.soldados.application.service.ports.in.CrearSoldadoUseCase;
import com.jcaa.udec.soldados.application.service.ports.in.ListarSoldadosUseCase;
import com.jcaa.udec.soldados.entrypoint.controller.dto.request.ActualizarSoldadoPeticion;
import com.jcaa.udec.soldados.entrypoint.controller.dto.request.RegistrarSoldadoPeticion;
import com.jcaa.udec.soldados.entrypoint.controller.dto.response.ObtenerSoldadoResponse;
import com.jcaa.udec.soldados.entrypoint.controller.mapper.SoldadoResponseMapper;

public class SoldadoControladorImpl implements SoldadoControlador {
    private final CrearSoldadoUseCase crearSoldadoUseCase;
    private final BuscarSoldadoUseCase buscarSoldadoUseCase;
    private final ListarSoldadosUseCase listarSoldadosUseCase;
    private final ActualizarSoldadoUseCase actualizarSoldadoUseCase;

    public SoldadoControladorImpl(
            CrearSoldadoUseCase crearSoldadoUseCase,
            BuscarSoldadoUseCase buscarSoldadoUseCase,
            ListarSoldadosUseCase listarSoldadosUseCase,
            ActualizarSoldadoUseCase actualizarSoldadoUseCase) {
        this.crearSoldadoUseCase = crearSoldadoUseCase;
        this.buscarSoldadoUseCase = buscarSoldadoUseCase;
        this.listarSoldadosUseCase = listarSoldadosUseCase;
        this.actualizarSoldadoUseCase = actualizarSoldadoUseCase;
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

    @Override
    public ObtenerSoldadoResponse obtenerTodos() {
        return SoldadoResponseMapper.mapearAResponse(listarSoldadosUseCase.obtenerTodos());
    }

    @Override
    public void actualizar(ActualizarSoldadoPeticion peticion) {
        ActualizarSoldadoComando comando = new ActualizarSoldadoComando(
                peticion.id(),
                peticion.nombreCompleto(),
                peticion.rango(),
                peticion.componenteMilitar());
        actualizarSoldadoUseCase.actualizar(comando);
    }
}
