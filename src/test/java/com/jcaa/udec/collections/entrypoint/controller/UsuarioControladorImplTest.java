package com.jcaa.udec.collections.entrypoint.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.CrearUsuarioComando;
import com.jcaa.udec.collections.application.service.dto.query.ObtenerUsuarioConsulta;
import com.jcaa.udec.collections.application.service.ports.in.AgregarUsuarioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerUsuarioUseCase;
import com.jcaa.udec.collections.domain.core.model.Usuario;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarUsuarioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerUsuarioResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.UsuarioResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class UsuarioControladorImplTest {
    private static final String ID = "123";
    private static final String PASSWORD = "ClaveSegura1!";
    private static final String NOMBRE = "Ana Perez";
    private static final String EMAIL = "ana_perez@example.com";

    @Test
    void deberiaRegistrarUsuario() {
        // Arrange
        AgregarUsuarioUseCaseStub agregarUsuarioUseCase = new AgregarUsuarioUseCaseStub();
        UsuarioControlador controlador =
                new UsuarioControladorImpl(agregarUsuarioUseCase, new ObtenerUsuarioUseCaseStub());
        RegistrarUsuarioPeticion peticion =
                new RegistrarUsuarioPeticion(ID, PASSWORD, NOMBRE, EMAIL);

        // Act
        controlador.registrar(peticion);

        // Assert
        assertThat(agregarUsuarioUseCase.getComandos())
                .singleElement()
                .extracting(
                        CrearUsuarioComando::id,
                        CrearUsuarioComando::password,
                        CrearUsuarioComando::nombre,
                        CrearUsuarioComando::email)
                .containsExactly(ID, PASSWORD, NOMBRE, EMAIL);
    }

    @Test
    void deberiaObtenerUsuarioPorId() {
        // Arrange
        ObtenerUsuarioUseCaseStub obtenerUsuarioUseCase = new ObtenerUsuarioUseCaseStub();
        UsuarioControlador controlador =
                new UsuarioControladorImpl(new AgregarUsuarioUseCaseStub(), obtenerUsuarioUseCase);

        // Act
        ObtenerUsuarioResponse response = controlador.obtenerPorId(ID);

        // Assert
        assertThat(obtenerUsuarioUseCase.getConsultas())
                .singleElement()
                .extracting(ObtenerUsuarioConsulta::id)
                .isEqualTo(ID);
        assertThat(response.toString())
                .contains("ID: " + ID, "PASSWORD: ****", "NOMBRE: " + NOMBRE, "EMAIL: " + EMAIL);
    }

    @Test
    void deberiaObtenerTodosLosUsuarios() {
        // Arrange
        UsuarioControlador controlador =
                new UsuarioControladorImpl(
                        new AgregarUsuarioUseCaseStub(), new ObtenerUsuarioUseCaseStub());

        // Act
        ObtenerUsuarioResponse response = controlador.obtenerTodos();

        // Assert
        assertThat(response.estaVacia()).isFalse();
        assertThat(response.usuarios())
                .singleElement()
                .extracting(UsuarioResponse::id, UsuarioResponse::nombre, UsuarioResponse::email)
                .containsExactly(ID, NOMBRE, EMAIL);
    }

    private static Usuario crearUsuario() {
        return new Usuario(ID, PASSWORD, NOMBRE, EMAIL);
    }

    private static final class AgregarUsuarioUseCaseStub implements AgregarUsuarioUseCase {
        private final List<CrearUsuarioComando> comandos = new ArrayList<>();

        @Override
        public void guardar(CrearUsuarioComando comando) {
            comandos.add(comando);
        }

        private List<CrearUsuarioComando> getComandos() {
            return List.copyOf(comandos);
        }
    }

    private static final class ObtenerUsuarioUseCaseStub implements ObtenerUsuarioUseCase {
        private final List<ObtenerUsuarioConsulta> consultas = new ArrayList<>();

        @Override
        public List<Usuario> obtenerTodos() {
            return List.of(crearUsuario());
        }

        @Override
        public Usuario obtenerPorId(ObtenerUsuarioConsulta consulta) {
            consultas.add(consulta);
            return crearUsuario();
        }

        private List<ObtenerUsuarioConsulta> getConsultas() {
            return List.copyOf(consultas);
        }
    }
}
