package com.jcaa.udec.collections.application.service.mapper;

import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.application.service.dto.command.CrearUsuarioComando;

public final class UsuarioMapper {
  private UsuarioMapper() {}

  public static Usuario mapearAUsuario(CrearUsuarioComando comando) {
    return Usuario.builder()
        .id(comando.id())
        .nombre(comando.nombre())
        .password(comando.password())
        .email(comando.email())
        .build();
  }
}
