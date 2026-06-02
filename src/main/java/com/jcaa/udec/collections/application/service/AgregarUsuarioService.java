package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.mapper.UsuarioMapper;
import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.application.service.dto.command.CrearUsuarioComando;
import com.jcaa.udec.collections.application.service.ports.in.AgregarUsuarioUseCase;
import com.jcaa.udec.collections.domain.port.out.GuardarUsuarioPort;

public class AgregarUsuarioService implements AgregarUsuarioUseCase {
  private final GuardarUsuarioPort guardarUsuarioPort;

  public AgregarUsuarioService(GuardarUsuarioPort guardarUsuarioPort) {
    this.guardarUsuarioPort = guardarUsuarioPort;
  }

  @Override
  public void guardar(CrearUsuarioComando comando) {
    Usuario usuario = UsuarioMapper.mapearAUsuario(comando);
    guardarUsuarioPort.guardar(usuario);
  }
}
