package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarUsuarioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerUsuarioResponse;

public interface UsuarioControlador {
    void registrar(RegistrarUsuarioPeticion peticion);

    ObtenerUsuarioResponse obtenerPorId(String id);

    ObtenerUsuarioResponse obtenerTodos();
}
