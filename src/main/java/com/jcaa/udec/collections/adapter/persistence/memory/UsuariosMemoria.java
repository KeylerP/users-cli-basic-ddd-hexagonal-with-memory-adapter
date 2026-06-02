package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.model.Usuario;

import java.util.ArrayList;
import java.util.List;

final class UsuariosMemoria {
    private static final List<Usuario> USUARIOS = new ArrayList<>();

    private UsuariosMemoria() {
    }

    static List<Usuario> obtenerUsuarios() {
        return USUARIOS;
    }
}
