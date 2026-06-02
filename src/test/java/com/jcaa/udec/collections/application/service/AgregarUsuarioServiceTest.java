package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.CrearUsuarioComando;
import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.domain.port.out.GuardarUsuarioPort;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AgregarUsuarioServiceTest {
  private static final String ID = "123";
  private static final String NOMBRE = "Ana Perez";
  private static final String PASSWORD = "ClaveSegura1!";
  private static final String EMAIL = "ana_perez@example.com";

  @Test
  void deberiaMapearYGuardarUsuario() {
    // Arrange
    GuardarUsuarioPortStub guardarUsuarioPort = new GuardarUsuarioPortStub();
    AgregarUsuarioService service = new AgregarUsuarioService(guardarUsuarioPort);
    CrearUsuarioComando comando = new CrearUsuarioComando(ID, NOMBRE, PASSWORD, EMAIL);

    // Act
    service.guardar(comando);

    // Assert
    assertThat(guardarUsuarioPort.getUsuariosGuardados())
        .singleElement()
        .extracting(Usuario::getId, Usuario::getNombre, Usuario::getEmail)
        .containsExactly(ID, NOMBRE, EMAIL);
  }

  private static final class GuardarUsuarioPortStub implements GuardarUsuarioPort {
    private final List<Usuario> usuariosGuardados = new ArrayList<>();

    @Override
    public void guardar(Usuario usuario) {
      usuariosGuardados.add(usuario);
    }

    private List<Usuario> getUsuariosGuardados() {
      return List.copyOf(usuariosGuardados);
    }
  }
}
