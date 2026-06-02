package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Usuario;

import java.util.List;

public interface ObtenerUsuariosPort {
    List<Usuario> obtenerTodos();

    Usuario buscarPorId(String id);
}
