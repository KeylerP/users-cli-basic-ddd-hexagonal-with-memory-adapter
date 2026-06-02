package com.jcaa.udec.collections.entrypoint.controller.dto.response;

public record ObtenerUsuarioResponse(UsuarioResponse usuario) {
  @Override
  public String toString() {
    return usuario.toString();
  }
}
