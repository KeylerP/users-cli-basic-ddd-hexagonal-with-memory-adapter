package com.jcaa.udec.soldados.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class ComponenteMilitarTest {

  private static final String COMPONENTE_VALIDO = "Batallon 1";

  @Test
  void deberiaCrearComponenteValido() {
    // Arrange
    // Act
    ComponenteMilitar componente = new ComponenteMilitar(COMPONENTE_VALIDO);

    // Assert
    assertThat(componente.valor()).isEqualTo(COMPONENTE_VALIDO);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "B"})
  void deberiaRechazarComponenteInvalido(String componente) {
    // Arrange
    // Act
    // Assert
    assertThatThrownBy(() -> new ComponenteMilitar(componente)).isInstanceOf(SoldadoInvalidoException.class);
  }
}
