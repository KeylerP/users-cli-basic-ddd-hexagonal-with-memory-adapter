package com.jcaa.udec.collections.entrypoint.controller.mapper;

import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerUsuarioResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.UsuarioResponse;

public final class UsuarioResponseMapper {
    private UsuarioResponseMapper() {
    }

    public static ObtenerUsuarioResponse mapearAResponse(Usuario usuario) {
        return new ObtenerUsuarioResponse(mapearAResponseUsuario(usuario));
    }

    private static UsuarioResponse mapearAResponseUsuario(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .build();
    }
}
