package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.UsuarioYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.domain.port.out.GuardarUsuarioPort;

import java.util.List;
import java.util.Objects;

public class GuardarUsuarioAdapter implements GuardarUsuarioPort {
    private final List<Usuario> usuarios = UsuariosMemoria.obtenerUsuarios();

    @Override
    public void guardar(Usuario usuario) {
        for (Usuario usuarioRegistrado : usuarios) {
            if (Objects.equals(usuarioRegistrado.getId(), usuario.getId())) {
                throw new UsuarioYaExisteException();
            }
        }
        usuarios.add(usuario);
    }
}
