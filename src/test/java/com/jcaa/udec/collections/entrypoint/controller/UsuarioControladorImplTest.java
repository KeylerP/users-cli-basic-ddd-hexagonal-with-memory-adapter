package com.jcaa.udec.collections.entrypoint.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.CrearUsuarioComando;
import com.jcaa.udec.collections.application.service.ports.in.AgregarUsuarioUseCase;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarUsuarioPeticion;
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
        UsuarioControlador controlador = new UsuarioControladorImpl(agregarUsuarioUseCase);
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
}
