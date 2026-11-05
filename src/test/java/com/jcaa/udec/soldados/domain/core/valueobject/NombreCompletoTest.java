package com.jcaa.udec.soldados.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NombreCompletoTest {

  private static final String NOMBRE_VALIDO = "Keyler Porto";

  @Test
  void deberiaCrearNombreValido() {
    // Arrange
    // Act
    NombreCompleto nombre = new NombreCompleto(NOMBRE_VALIDO);

    // Assert
    assertThat(nombre.valor()).isEqualTo(NOMBRE_VALIDO);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "An"})
  void deberiaRechazarNombreInvalido(String nombre) {
    // Arrange
    // Act
    // Assert
    assertThatThrownBy(() -> new NombreCompleto(nombre)).isInstanceOf(SoldadoInvalidoException.class);
  }
}
