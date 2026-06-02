package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordTest {
    private static final String PASSWORD_VALIDO = "ClaveSegura1!";

    @Test
    void deberiaCrearPasswordValido() {
        // Arrange
        // Act
        Password password = new Password(PASSWORD_VALIDO);

        // Assert
        assertThat(password.valor()).isEqualTo(PASSWORD_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"Clave1!", "CLAVESEGURA1!", "clavesegura1!", "ClaveSegura!", "ClaveSegura1"})
    void deberiaRechazarPasswordInvalido(String password) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Password(password))
                .isInstanceOf(UsuarioInvalidoException.class);
    }
}
