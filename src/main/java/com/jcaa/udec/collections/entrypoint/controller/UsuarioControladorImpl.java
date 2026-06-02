package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.application.service.dto.command.CrearUsuarioComando;
import com.jcaa.udec.collections.application.service.ports.in.AgregarUsuarioUseCase;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarUsuarioPeticion;

public class UsuarioControladorImpl implements UsuarioControlador {
    private final AgregarUsuarioUseCase agregarUsuarioUseCase;

    public UsuarioControladorImpl(AgregarUsuarioUseCase agregarUsuarioUseCase) {
        this.agregarUsuarioUseCase = agregarUsuarioUseCase;
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
}
