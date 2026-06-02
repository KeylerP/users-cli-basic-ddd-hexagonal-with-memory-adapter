package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NombreUsuarioTest {
    private static final String NOMBRE_VALIDO = "Ana Perez";

    @Test
    void deberiaCrearNombreValido() {
        // Arrange
        // Act
        NombreUsuario nombreUsuario = new NombreUsuario(NOMBRE_VALIDO);

        // Assert
        assertThat(nombreUsuario.valor()).isEqualTo(NOMBRE_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "An"})
    void deberiaRechazarNombreInvalido(String nombre) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new NombreUsuario(nombre))
                .isInstanceOf(UsuarioInvalidoException.class);
    }
}
