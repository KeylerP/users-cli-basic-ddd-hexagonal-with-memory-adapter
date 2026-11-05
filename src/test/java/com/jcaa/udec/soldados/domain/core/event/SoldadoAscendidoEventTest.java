package com.jcaa.udec.soldados.domain.core.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import com.jcaa.udec.soldados.domain.core.valueobject.SoldadoId;
import org.junit.jupiter.api.Test;

class SoldadoAscendidoEventTest {

  @Test
  void deberiaCrearElEventoConLosDatosDelAscenso() {
    // Arrange
    SoldadoId id = new SoldadoId("1234567");
    Rango anterior = new Rango("Soldado Profesional");
    Rango nuevo = new Rango("Cabo Segundo");

    // Act
    SoldadoAscendidoEvent evento = new SoldadoAscendidoEvent(id, anterior, nuevo, null);

    // Assert
    assertThat(evento.soldadoId()).isEqualTo(id);
    assertThat(evento.rangoAnterior()).isEqualTo(anterior);
    assertThat(evento.rangoNuevo()).isEqualTo(nuevo);
    assertThat(evento.ocurridoEn()).isNotNull();
  }

  @Test
  void deberiaRechazarCreacionSinSoldadoId() {
    // Arrange
    Rango anterior = new Rango("Soldado Profesional");
    Rango nuevo = new Rango("Cabo Segundo");

    // Act
    // Assert
    assertThatThrownBy(() -> new SoldadoAscendidoEvent(null, anterior, nuevo, null))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
