package com.jcaa.udec.soldados.domain.core.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import org.junit.jupiter.api.Test;

class ValidacionAscensoPorMeritoTest {

  @Test
  void deberiaPermitirElSiguienteRangoYUnoAdicional() {
    // Arrange
    Soldado soldado = new Soldado("1234567", "Keyler Porto", "Soldado Profesional", "Batallon 1");
    EstrategiaValidacionAscenso estrategia = new ValidacionAscensoPorMerito();

    // Act
    var candidatos = estrategia.rangosValidosPara(soldado, new PoliticaAscensoMilitar());

    // Assert
    assertThat(candidatos).extracting(Rango::valor).containsExactly("Cabo Segundo", "Cabo Primero");
  }
}
