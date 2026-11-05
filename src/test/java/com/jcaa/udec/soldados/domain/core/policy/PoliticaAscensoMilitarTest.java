package com.jcaa.udec.soldados.domain.core.policy;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import org.junit.jupiter.api.Test;

class PoliticaAscensoMilitarTest {

  private final PoliticaAscensoMilitar politica = new PoliticaAscensoMilitar();

  @Test
  void deberiaDevolverElSiguienteRangoInmediato() {
    // Arrange
    // Act
    var siguientes = politica.siguientesRangosValidos(new Rango("Soldado Profesional"));

    // Assert
    assertThat(siguientes).extracting(Rango::valor).containsExactly("Cabo Segundo");
  }

  @Test
  void deberiaDevolverDosRangosConLaEstrategiaDeMerito() {
    // Arrange
    // Act
    var siguientes = politica.siguientesRangosValidosPorMerito(new Rango("Soldado Profesional"));

    // Assert
    assertThat(siguientes).extracting(Rango::valor).containsExactly("Cabo Segundo", "Cabo Primero");
  }

  @Test
  void noDeberiaHaberSiguienteRangoParaElUltimoNivel() {
    // Arrange
    // Act
    var siguientes = politica.siguientesRangosValidos(new Rango("Sargento Mayor"));

    // Assert
    assertThat(siguientes).isEmpty();
    assertThat(politica.esUltimoRango(new Rango("Sargento Mayor"))).isTrue();
  }

  @Test
  void unRangoFueraDeLaJerarquiaNoDebeTenerSiguientes() {
    // Arrange
    // Act
    var siguientes = politica.siguientesRangosValidos(new Rango("Rango Inexistente"));

    // Assert
    assertThat(siguientes).isEmpty();
  }
}
