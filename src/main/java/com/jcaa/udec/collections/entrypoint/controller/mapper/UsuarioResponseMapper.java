package com.jcaa.udec.collections.entrypoint.controller.mapper;

import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerUsuarioResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.UsuarioResponse;
import java.util.List;

public final class UsuarioResponseMapper {
    private UsuarioResponseMapper() {
    }

    public static ObtenerUsuarioResponse mapearAResponse(Usuario usuario) {
        return new ObtenerUsuarioResponse(List.of(mapearAResponseUsuario(usuario)));
    }

    public static ObtenerUsuarioResponse mapearAResponse(List<Usuario> usuarios) {
        return new ObtenerUsuarioResponse(usuarios.stream()
                .map(UsuarioResponseMapper::mapearAResponseUsuario)
                .toList());
    }

    private static UsuarioResponse mapearAResponseUsuario(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .build();
    }
}
