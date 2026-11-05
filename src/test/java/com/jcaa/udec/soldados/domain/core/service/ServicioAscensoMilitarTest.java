package com.jcaa.udec.soldados.domain.core.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.soldados.domain.core.exception.AscensoInvalidoException;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.strategy.EstrategiaValidacionAscenso;
import com.jcaa.udec.soldados.domain.core.strategy.ValidacionAscensoEstandar;
import com.jcaa.udec.soldados.domain.core.strategy.ValidacionAscensoPorMerito;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import org.junit.jupiter.api.Test;

/**
 * Test del Servicio de Dominio ServicioAscensoMilitar (pregunta 54 del informe). No usa mocks de
 * infraestructura: el servicio solo colabora con otros objetos de dominio (Soldado, Politica,
 * Strategy), por lo que la prueba es rapida y no necesita levantar nada externo.
 */
class ServicioAscensoMilitarTest {

  private final PoliticaAscensoMilitar politica = new PoliticaAscensoMilitar();
  private final ServicioAscensoMilitar servicio = new ServicioAscensoMilitar(politica);
  private final Soldado soldado = new Soldado("1234567", "Keyler Porto", "Soldado Profesional", "Batallon 1");

  @Test
  void deberiaAscenderConEstrategiaEstandarYEmitirElEvento() {
    // Arrange
    EstrategiaValidacionAscenso estrategia = new ValidacionAscensoEstandar();

    // Act
    ServicioAscensoMilitar.ResultadoAscenso resultado =
        servicio.ascender(soldado, new Rango("Cabo Segundo"), estrategia);

    // Assert
    assertThat(resultado.soldadoActualizado().getRango().valor()).isEqualTo("Cabo Segundo");
    assertThat(resultado.evento().rangoAnterior().valor()).isEqualTo("Soldado Profesional");
    assertThat(resultado.evento().rangoNuevo().valor()).isEqualTo("Cabo Segundo");
    assertThat(resultado.evento().soldadoId()).isEqualTo(soldado.getId());
  }

  @Test
  void deberiaRechazarUnSaltoDeNivelesConEstrategiaEstandar() {
    // Arrange
    EstrategiaValidacionAscenso estrategia = new ValidacionAscensoEstandar();

    // Act
    // Assert
    assertThatThrownBy(() -> servicio.ascender(soldado, new Rango("Sargento Segundo"), estrategia))
        .isInstanceOf(AscensoInvalidoException.class);
  }

  @Test
  void deberiaPermitirUnSaltoDeUnNivelAdicionalConEstrategiaPorMerito() {
    // Arrange
    EstrategiaValidacionAscenso estrategia = new ValidacionAscensoPorMerito();

    // Act
    ServicioAscensoMilitar.ResultadoAscenso resultado =
        servicio.ascender(soldado, new Rango("Cabo Primero"), estrategia);

    // Assert
    assertThat(resultado.soldadoActualizado().getRango().valor()).isEqualTo("Cabo Primero");
  }
}
