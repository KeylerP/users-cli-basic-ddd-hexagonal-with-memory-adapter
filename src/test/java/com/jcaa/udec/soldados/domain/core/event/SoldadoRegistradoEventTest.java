package com.jcaa.udec.soldados.domain.core.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.soldados.domain.core.valueobject.NombreCompleto;
import com.jcaa.udec.soldados.domain.core.valueobject.SoldadoId;
import org.junit.jupiter.api.Test;

class SoldadoRegistradoEventTest {

  @Test
  void deberiaCrearElEventoConLosDatosDelSoldadoRegistrado() {
    // Arrange
    SoldadoId id = new SoldadoId("1234567");
    NombreCompleto nombre = new NombreCompleto("Keyler Porto");

    // Act
    SoldadoRegistradoEvent evento = new SoldadoRegistradoEvent(id, nombre, null);

    // Assert
    assertThat(evento.soldadoId()).isEqualTo(id);
    assertThat(evento.nombreCompleto()).isEqualTo(nombre);
    assertThat(evento.ocurridoEn()).isNotNull();
  }

  @Test
  void deberiaRechazarCreacionSinNombreCompleto() {
    // Arrange
    SoldadoId id = new SoldadoId("1234567");

    // Act
    // Assert
    assertThatThrownBy(() -> new SoldadoRegistradoEvent(id, null, null))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
