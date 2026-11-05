package com.jcaa.udec.soldados.domain.core.factory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SoldadoFactoryTest {

  @Test
  void deberiaCrearSoldadoSiempreEnElRangoBase() {
    // Arrange
    SoldadoFactory factory = new SoldadoFactory();

    // Act
    SoldadoFactory.SoldadoCreado creado = factory.crear("1234567", "Keyler Porto", "Batallon 1");

    // Assert
    assertThat(creado.soldado().getRango().valor()).isEqualTo(SoldadoFactory.RANGO_BASE);
  }

  @Test
  void deberiaEmitirSoldadoRegistradoEventConElMismoId() {
    // Arrange
    SoldadoFactory factory = new SoldadoFactory();

    // Act
    SoldadoFactory.SoldadoCreado creado = factory.crear("1234567", "Keyler Porto", "Batallon 1");

    // Assert
    assertThat(creado.evento().soldadoId()).isEqualTo(creado.soldado().getId());
    assertThat(creado.evento().nombreCompleto()).isEqualTo(creado.soldado().getNombreCompleto());
  }
}
