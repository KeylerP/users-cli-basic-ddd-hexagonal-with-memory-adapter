package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.UsuarioNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.domain.port.out.ObtenerUsuariosPort;

import java.util.List;
import java.util.Objects;

public class ObtenerUsuariosAdapter implements ObtenerUsuariosPort {
    private final List<Usuario> usuarios = UsuariosMemoria.obtenerUsuarios();

    @Override
    public List<Usuario> obtenerTodos() {
        return List.copyOf(usuarios);
    }

    @Override
    public Usuario buscarPorId(String id) {
        for (Usuario usuario : usuarios) {
            if (Objects.equals(usuario.getId(), id)) {
                return usuario;
            }
        }
        throw new UsuarioNoExisteException();
    }
}
