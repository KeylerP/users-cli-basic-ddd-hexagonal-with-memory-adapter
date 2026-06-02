package com.jcaa.udec.collections.adapter.persistence.memory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.UsuarioNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.UsuarioYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Usuario;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsuariosAdapterTest {
    private static final AtomicInteger SECUENCIA_ID = new AtomicInteger(1000);
    private final GuardarUsuarioAdapter guardarUsuarioAdapter = new GuardarUsuarioAdapter();
    private final ObtenerUsuariosAdapter obtenerUsuariosAdapter = new ObtenerUsuariosAdapter();

    @BeforeEach
    void limpiarUsuarios() {
        UsuariosMemoria.obtenerUsuarios().clear();
    }

    @Test
    void deberiaGuardarYObtenerUsuario() {
        // Arrange
        Usuario usuario = crearUsuario();

        // Act
        guardarUsuarioAdapter.guardar(usuario);
        Usuario usuarioEncontrado = obtenerUsuariosAdapter.buscarPorId(usuario.getId());

        // Assert
        assertThat(usuarioEncontrado).isSameAs(usuario);
    }

    @Test
    void deberiaRechazarUsuarioDuplicado() {
        // Arrange
        Usuario usuario = crearUsuario();
        guardarUsuarioAdapter.guardar(usuario);

        // Act
        // Assert
        assertThatThrownBy(() -> guardarUsuarioAdapter.guardar(usuario))
                .isInstanceOf(UsuarioYaExisteException.class)
                .hasMessage("El usuario ya existe.");
    }

    @Test
    void deberiaReportarUsuarioInexistente() {
        // Arrange
        String idInexistente = String.valueOf(SECUENCIA_ID.incrementAndGet());

        // Act
        // Assert
        assertThatThrownBy(() -> obtenerUsuariosAdapter.buscarPorId(idInexistente))
                .isInstanceOf(UsuarioNoExisteException.class)
                .hasMessage("El usuario no existe.");
    }

    @Test
    void deberiaReportarUsuarioInexistenteCuandoHayUsuariosGuardados() {
        // Arrange
        Usuario usuario = crearUsuario();
        guardarUsuarioAdapter.guardar(usuario);
        String idInexistente = String.valueOf(SECUENCIA_ID.incrementAndGet());

        // Act
        // Assert
        assertThatThrownBy(() -> obtenerUsuariosAdapter.buscarPorId(idInexistente))
                .isInstanceOf(UsuarioNoExisteException.class)
                .hasMessage("El usuario no existe.");
    }

    @Test
    void deberiaRetornarUnaListaInmutable() {
        // Arrange
        List<Usuario> usuarios = obtenerUsuariosAdapter.obtenerTodos();

        // Act
        // Assert
        assertThatThrownBy(() -> usuarios.add(crearUsuario()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void deberiaObtenerTodosLosUsuariosGuardados() {
        // Arrange
        Usuario primerUsuario = crearUsuario();
        Usuario segundoUsuario = crearUsuario();
        guardarUsuarioAdapter.guardar(primerUsuario);
        guardarUsuarioAdapter.guardar(segundoUsuario);

        // Act
        List<Usuario> usuarios = obtenerUsuariosAdapter.obtenerTodos();

        // Assert
        assertThat(usuarios).containsExactly(primerUsuario, segundoUsuario);
    }

    private static Usuario crearUsuario() {
        String id = String.valueOf(SECUENCIA_ID.incrementAndGet());
        return new Usuario(id, "ClaveSegura1!", "Ana Perez", "ana_perez@example.com");
    }
}
