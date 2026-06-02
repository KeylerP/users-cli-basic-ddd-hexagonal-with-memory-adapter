package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EmailTest {
    private static final String EMAIL_VALIDO = "ana_perez@example.com";

    @Test
    void deberiaCrearEmailValido() {
        // Arrange
        // Act
        Email email = new Email(EMAIL_VALIDO);

        // Assert
        assertThat(email.valor()).isEqualTo(EMAIL_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
        " ",
        "ana perez@example.com",
        "correo-invalido",
        "ana@@example.com",
        "ana@",
        "a@example.com",
        "ana@x.co",
        "ana@examplecom",
        "ana-perez@example.com",
        "ana@example-com"
    })
    void deberiaRechazarEmailInvalido(String email) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Email(email))
                .isInstanceOf(UsuarioInvalidoException.class);
    }
}
