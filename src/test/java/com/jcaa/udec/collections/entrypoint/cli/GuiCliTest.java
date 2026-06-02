package com.jcaa.udec.collections.entrypoint.cli;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.domain.core.exception.UsuarioYaExisteException;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControlador;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarUsuarioPeticion;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class GuiCliTest {
    private static final String ID = "123";
    private static final String PASSWORD = "ClaveSegura1!";
    private static final String NOMBRE = "Ana Perez";
    private static final String EMAIL = "ana_perez@example.com";

    @Test
    void deberiaSolicitarOpcionHastaRecibirValorValido() {
        // Arrange
        UsuarioControladorStub controlador = new UsuarioControladorStub();
        GuiCli guiCli = crearGuiCli(controlador, "texto", "2", "\uFEFF4");

        // Act
        String salida = capturarSalida(() -> assertThat(guiCli.obtenerOpcionMenu()).isEqualTo(4));

        // Assert
        assertThat(salida).contains("Opcion [texto] invalida", "Opcion [2] invalida");
    }

    @Test
    void deberiaRegistrarUsuarioLuegoDeCorregirDatosInvalidos() {
        // Arrange
        UsuarioControladorStub controlador = new UsuarioControladorStub();
        GuiCli guiCli = crearGuiCli(
                controlador,
                "1",
                "abc",
                ID,
                "Clave1!",
                PASSWORD,
                "An",
                NOMBRE,
                "correo-invalido",
                EMAIL,
                "4");

        // Act
        String salida = capturarSalida(guiCli::ejecutarAccion);

        // Assert
        assertThat(controlador.getPeticiones())
                .singleElement()
                .extracting(
                        RegistrarUsuarioPeticion::id,
                        RegistrarUsuarioPeticion::password,
                        RegistrarUsuarioPeticion::nombre,
                        RegistrarUsuarioPeticion::email)
                .containsExactly(ID, PASSWORD, NOMBRE, EMAIL);
        assertThat(salida).contains(
                "ID INVALIDO",
                "PASSWORD INVALIDO",
                "NOMBRE INVALIDO",
                "EMAIL INVALIDO",
                "Usuario registrado correctamente.",
                "Esperamos tu regreso. Bye, Bye");
    }

    @Test
    void deberiaInformarErrorCuandoElUsuarioYaExiste() {
        // Arrange
        UsuarioControladorStub controlador = new UsuarioControladorStub();
        controlador.reportarUsuarioDuplicado();
        GuiCli guiCli = crearGuiCli(controlador, "1", ID, PASSWORD, NOMBRE, EMAIL, "4");

        // Act
        String salida = capturarSalida(guiCli::ejecutarAccion);

        // Assert
        assertThat(salida).contains("ERROR: El usuario ya existe.");
    }

    private static GuiCli crearGuiCli(UsuarioControlador controlador, String... entradas) {
        String contenido = String.join(System.lineSeparator(), entradas) + System.lineSeparator();
        return new GuiCli(controlador, new Scanner(contenido));
    }

    private static String capturarSalida(Runnable accion) {
        PrintStream salidaOriginal = System.out;
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida, true, StandardCharsets.UTF_8));
        try {
            accion.run();
            return salida.toString(StandardCharsets.UTF_8);
        } finally {
            System.setOut(salidaOriginal);
        }
    }

    private static final class UsuarioControladorStub implements UsuarioControlador {
        private final List<RegistrarUsuarioPeticion> peticiones = new ArrayList<>();
        private boolean usuarioDuplicado;

        @Override
        public void registrar(RegistrarUsuarioPeticion peticion) {
            if (usuarioDuplicado) {
                throw new UsuarioYaExisteException();
            }
            peticiones.add(peticion);
        }


        private List<RegistrarUsuarioPeticion> getPeticiones() {
            return List.copyOf(peticiones);
        }

        private void reportarUsuarioDuplicado() {
            usuarioDuplicado = true;
        }
    }
}
