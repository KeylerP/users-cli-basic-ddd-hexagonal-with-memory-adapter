package com.jcaa.udec.soldados.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.soldados.domain.core.exception.SoldadoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Test del Value Object Rango (pregunta 52 del informe: diseño de test de un VO del CEA). Valida
 * las dos garantias que debe dar todo VO: valor valido se guarda tal cual, y ningun estado
 * invalido puede siquiera llegar a existir como objeto (falla en el constructor, no despues).
 */
class RangoTest {

  private static final String RANGO_VALIDO = "Cabo Segundo";

  @Test
  void deberiaCrearRangoValido() {
    // Arrange
    // Act
    Rango rango = new Rango(RANGO_VALIDO);

    // Assert
    assertThat(rango.valor()).isEqualTo(RANGO_VALIDO);
  }

  @Test
  void dosRangosConElMismoValorDebenSerIguales() {
    // Arrange
    // Act
    Rango rango1 = new Rango(RANGO_VALIDO);
    Rango rango2 = new Rango(RANGO_VALIDO);

    // Assert
    assertThat(rango1).isEqualTo(rango2);
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {" ", "C"})
  void deberiaRechazarRangoInvalido(String rango) {
    // Arrange
    // Act
    // Assert
    assertThatThrownBy(() -> new Rango(rango)).isInstanceOf(SoldadoInvalidoException.class);
  }
}
