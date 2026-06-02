package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.CrearUsuarioComando;

public interface AgregarUsuarioUseCase {
  void guardar(CrearUsuarioComando comando);
}
