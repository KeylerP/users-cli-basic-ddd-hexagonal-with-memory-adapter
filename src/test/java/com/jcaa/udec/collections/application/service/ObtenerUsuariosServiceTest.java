package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.query.ObtenerUsuarioConsulta;
import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.domain.port.out.ObtenerUsuariosPort;
import java.util.List;
import org.junit.jupiter.api.Test;

class ObtenerUsuariosServiceTest {
  private static final String ID = "123";
  private static final String NOMBRE = "Ana Perez";
  private static final String PASSWORD = "ClaveSegura1!";
  private static final String EMAIL = "ana_perez@example.com";

  @Test
  void deberiaObtenerTodosLosUsuarios() {
    // Arrange
    Usuario usuario = crearUsuario();
    List<Usuario> usuariosEsperados = List.of(usuario);
    ObtenerUsuariosPortStub obtenerUsuariosPort =
        new ObtenerUsuariosPortStub(usuariosEsperados, usuario);
    ObtenerUsuariosService service = new ObtenerUsuariosService(obtenerUsuariosPort);

    // Act
    List<Usuario> usuarios = service.obtenerTodos();

    // Assert
    assertThat(usuarios).containsExactly(usuario);
  }

  @Test
  void deberiaObtenerUsuarioPorId() {
    // Arrange
    Usuario usuarioEsperado = crearUsuario();
    ObtenerUsuariosPortStub obtenerUsuariosPort =
        new ObtenerUsuariosPortStub(List.of(usuarioEsperado), usuarioEsperado);
    ObtenerUsuariosService service = new ObtenerUsuariosService(obtenerUsuariosPort);
    ObtenerUsuarioConsulta consulta = new ObtenerUsuarioConsulta(ID);

    // Act
    Usuario usuario = service.obtenerPorId(consulta);

    // Assert
    assertThat(usuario).isSameAs(usuarioEsperado);
    assertThat(obtenerUsuariosPort.getIdConsultado()).isEqualTo(ID);
  }

  private static Usuario crearUsuario() {
    return new Usuario(ID, PASSWORD, NOMBRE, EMAIL);
  }

  private static final class ObtenerUsuariosPortStub implements ObtenerUsuariosPort {
    private final List<Usuario> usuarios;
    private final Usuario usuario;
    private String idConsultado;

    private ObtenerUsuariosPortStub(List<Usuario> usuarios, Usuario usuario) {
      this.usuarios = usuarios;
      this.usuario = usuario;
    }

    @Override
    public List<Usuario> obtenerTodos() {
      return usuarios;
    }

    @Override
    public Usuario buscarPorId(String id) {
      idConsultado = id;
      return usuario;
    }

    private String getIdConsultado() {
      return idConsultado;
    }
  }
}
