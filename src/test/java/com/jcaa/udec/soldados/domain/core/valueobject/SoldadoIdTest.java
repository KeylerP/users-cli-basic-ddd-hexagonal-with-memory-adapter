package com.jcaa.udec.soldados.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class SoldadoIdTest {

  private static final String ID_VALIDO = "1234567";

  @Test
  void deberiaCrearIdValido() {
    // Arrange
    // Act
    SoldadoId id = new SoldadoId(ID_VALIDO);

    // Assert
    assertThat(id.valor()).isEqualTo(ID_VALIDO);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "123", "abcdefg", "12a4567"})
  void deberiaRechazarIdInvalido(String id) {
    // Arrange
    // Act
    // Assert
    assertThatThrownBy(() -> new SoldadoId(id)).isInstanceOf(SoldadoInvalidoException.class);
  }
}
