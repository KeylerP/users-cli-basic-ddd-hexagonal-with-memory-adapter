package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.application.service.dto.command.CrearUsuarioComando;
import com.jcaa.udec.collections.application.service.dto.query.ObtenerUsuarioConsulta;
import com.jcaa.udec.collections.application.service.ports.in.AgregarUsuarioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerUsuarioUseCase;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarUsuarioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerUsuarioResponse;
import com.jcaa.udec.collections.entrypoint.controller.mapper.UsuarioResponseMapper;

public class UsuarioControladorImpl implements UsuarioControlador {
    private final AgregarUsuarioUseCase agregarUsuarioUseCase;
    private final ObtenerUsuarioUseCase obtenerUsuarioUseCase;

    public UsuarioControladorImpl(
            AgregarUsuarioUseCase agregarUsuarioUseCase,
            ObtenerUsuarioUseCase obtenerUsuarioUseCase) {
        this.agregarUsuarioUseCase = agregarUsuarioUseCase;
        this.obtenerUsuarioUseCase = obtenerUsuarioUseCase;
    }

    @Override
    public void registrar(RegistrarUsuarioPeticion peticion) {
        CrearUsuarioComando comando = new CrearUsuarioComando(
                peticion.id(),
                peticion.nombre(),
                peticion.password(),
                peticion.email());
        agregarUsuarioUseCase.guardar(comando);
    }

    @Override
    public ObtenerUsuarioResponse obtenerPorId(String id) {
        ObtenerUsuarioConsulta consulta = new ObtenerUsuarioConsulta(id);
        return UsuarioResponseMapper.mapearAResponse(obtenerUsuarioUseCase.obtenerPorId(consulta));
    }

    @Override
    public ObtenerUsuarioResponse obtenerTodos() {
        return UsuarioResponseMapper.mapearAResponse(obtenerUsuarioUseCase.obtenerTodos());
    }
}
