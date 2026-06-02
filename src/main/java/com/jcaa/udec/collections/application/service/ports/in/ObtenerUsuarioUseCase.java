package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.application.service.dto.query.ObtenerUsuarioConsulta;

import java.util.List;

public interface ObtenerUsuarioUseCase {
  List<Usuario> obtenerTodos();

  Usuario obtenerPorId(ObtenerUsuarioConsulta consulta);
}
