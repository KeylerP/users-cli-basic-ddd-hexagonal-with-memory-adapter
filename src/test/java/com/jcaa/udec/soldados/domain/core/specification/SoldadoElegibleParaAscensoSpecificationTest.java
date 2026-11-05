package com.jcaa.udec.soldados.domain.core.specification;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import org.junit.jupiter.api.Test;

class SoldadoElegibleParaAscensoSpecificationTest {

  private final PoliticaAscensoMilitar politica = new PoliticaAscensoMilitar();
  private final Soldado soldado = new Soldado("1234567", "Keyler Porto", "Soldado Profesional", "Batallon 1");

  @Test
  void deberiaSerElegibleParaElSiguienteRangoInmediato() {
    // Arrange
    SoldadoElegibleParaAscensoSpecification spec =
        new SoldadoElegibleParaAscensoSpecification(new Rango("Cabo Segundo"), politica);

    // Act
    // Assert
    assertThat(spec.isSatisfiedBy(soldado)).isTrue();
  }

  @Test
  void noDeberiaSerElegibleParaUnRangoLejano() {
    // Arrange
    SoldadoElegibleParaAscensoSpecification spec =
        new SoldadoElegibleParaAscensoSpecification(new Rango("Sargento Segundo"), politica);

    // Act
    // Assert
    assertThat(spec.isSatisfiedBy(soldado)).isFalse();
  }

  @Test
  void elOperadorOrDebeCombinarDosSpecifications() {
    // Arrange
    SoldadoElegibleParaAscensoSpecification elegibleCabo =
        new SoldadoElegibleParaAscensoSpecification(new Rango("Cabo Segundo"), politica);
    SoldadoElegibleParaAscensoSpecification elegibleSargento =
        new SoldadoElegibleParaAscensoSpecification(new Rango("Sargento Segundo"), politica);

    // Act
    // Assert
    assertThat(elegibleCabo.or(elegibleSargento).isSatisfiedBy(soldado)).isTrue();
  }

  @Test
  void elOperadorNotDebeInvertirElResultado() {
    // Arrange
    SoldadoElegibleParaAscensoSpecification elegibleCabo =
        new SoldadoElegibleParaAscensoSpecification(new Rango("Cabo Segundo"), politica);

    // Act
    // Assert
    assertThat(elegibleCabo.not().isSatisfiedBy(soldado)).isFalse();
  }
}
