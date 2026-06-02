package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.application.service.dto.query.ObtenerUsuarioConsulta;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerUsuarioUseCase;
import com.jcaa.udec.collections.domain.port.out.ObtenerUsuariosPort;

import java.util.List;

public class ObtenerUsuariosService implements ObtenerUsuarioUseCase {
  private final ObtenerUsuariosPort obtenerUsuariosPort;

  public ObtenerUsuariosService(ObtenerUsuariosPort obtenerUsuariosPort) {
    this.obtenerUsuariosPort = obtenerUsuariosPort;
  }

  @Override
  public List<Usuario> obtenerTodos() {
    return obtenerUsuariosPort.obtenerTodos();
  }

  @Override
  public Usuario obtenerPorId(ObtenerUsuarioConsulta consulta) {
    return obtenerUsuariosPort.buscarPorId(consulta.id());
  }
}
