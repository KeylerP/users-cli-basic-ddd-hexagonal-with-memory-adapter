package com.jcaa.udec.soldados.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.soldados.domain.core.exception.AscensoInvalidoException;
import com.jcaa.udec.soldados.domain.core.exception.SoldadoInvalidoException;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Test del Agregado Soldado (pregunta 53 del informe: test de una Entidad/Agregado que verifique
 * una invariante). La invariante bajo prueba es la de la Raiz de Agregado: el Rango solo puede
 * avanzar siguiendo el orden jerarquico oficial que define la PoliticaAscensoMilitar.
 */
class SoldadoTest {

  private static final String ID_VALIDO = "1234567";
  private static final String NOMBRE_VALIDO = "Keyler Porto";
  private static final String COMPONENTE_VALIDO = "Batallon 1";
  private static final String MENSAJE_DATOS_INVALIDOS = "Los datos del soldado son invalidos.";

  @Test
  void deberiaCrearSoldadoConDatosValidos() {
    // Arrange
    // Act
    Soldado soldado = new Soldado(ID_VALIDO, NOMBRE_VALIDO, "Soldado Profesional", COMPONENTE_VALIDO);

    // Assert
    assertThat(soldado)
        .extracting(s -> s.getId().valor(), s -> s.getNombreCompleto().valor(), s -> s.getRango().valor())
        .containsExactly(ID_VALIDO, NOMBRE_VALIDO, "Soldado Profesional");
    assertThat(soldado.getHistorialDeAscensos()).isEmpty();
  }

  @Test
  void deberiaCrearSoldadoConBuilder() {
    // Arrange
    // Act
    Soldado soldado =
        Soldado.builder()
            .id(ID_VALIDO)
            .nombreCompleto(NOMBRE_VALIDO)
            .rango("Soldado Profesional")
            .componenteMilitar(COMPONENTE_VALIDO)
            .build();

    // Assert
    assertThat(soldado.getId().valor()).isEqualTo(ID_VALIDO);
  }

  @ParameterizedTest
  @MethodSource("datosInvalidos")
  void deberiaRechazarSoldadoConDatosInvalidos(String id, String nombre, String rango, String componente) {
    // Arrange
    // Act
    // Assert
    assertThatThrownBy(() -> new Soldado(id, nombre, rango, componente))
        .isInstanceOf(SoldadoInvalidoException.class);
  }

  private static Stream<Arguments> datosInvalidos() {
    return Stream.of(
        Arguments.of("abc", NOMBRE_VALIDO, "Soldado Profesional", COMPONENTE_VALIDO),
        Arguments.of(ID_VALIDO, "An", "Soldado Profesional", COMPONENTE_VALIDO),
        Arguments.of(ID_VALIDO, NOMBRE_VALIDO, "S", COMPONENTE_VALIDO),
        Arguments.of(ID_VALIDO, NOMBRE_VALIDO, "Soldado Profesional", "B"));
  }

  @Test
  void deberiaAscenderUnNivelCuandoElRangoDestinoEsElSiguienteEnLaJerarquia() {
    // Arrange
    Soldado soldado = new Soldado(ID_VALIDO, NOMBRE_VALIDO, "Soldado Profesional", COMPONENTE_VALIDO);
    PoliticaAscensoMilitar politica = new PoliticaAscensoMilitar();

    // Act
    Soldado ascendido = soldado.ascenderA(new Rango("Cabo Segundo"), politica);

    // Assert
    assertThat(ascendido.getRango().valor()).isEqualTo("Cabo Segundo");
    assertThat(ascendido.getHistorialDeAscensos()).hasSize(1);
    assertThat(ascendido.getHistorialDeAscensos().get(0).rangoAnterior().valor()).isEqualTo("Soldado Profesional");
    assertThat(ascendido.getHistorialDeAscensos().get(0).rangoNuevo().valor()).isEqualTo("Cabo Segundo");
  }

  @Test
  void noDeberiaMutarElSoldadoOriginalAlAscender() {
    // Arrange
    Soldado soldado = new Soldado(ID_VALIDO, NOMBRE_VALIDO, "Soldado Profesional", COMPONENTE_VALIDO);

    // Act
    soldado.ascenderA(new Rango("Cabo Segundo"), new PoliticaAscensoMilitar());

    // Assert
    assertThat(soldado.getRango().valor()).isEqualTo("Soldado Profesional");
    assertThat(soldado.getHistorialDeAscensos()).isEmpty();
  }

  @Test
  void deberiaRechazarAscensoQueSalteNivelesDeLaJerarquia() {
    // Arrange
    Soldado soldado = new Soldado(ID_VALIDO, NOMBRE_VALIDO, "Soldado Profesional", COMPONENTE_VALIDO);
    PoliticaAscensoMilitar politica = new PoliticaAscensoMilitar();

    // Act
    // Assert
    assertThatThrownBy(() -> soldado.ascenderA(new Rango("Sargento Segundo"), politica))
        .isInstanceOf(AscensoInvalidoException.class);
  }

  @Test
  void deberiaRechazarIntentoDeDescensoDeRango() {
    // Arrange
    Soldado soldado = new Soldado(ID_VALIDO, NOMBRE_VALIDO, "Cabo Segundo", COMPONENTE_VALIDO);
    PoliticaAscensoMilitar politica = new PoliticaAscensoMilitar();

    // Act
    // Assert
    assertThatThrownBy(() -> soldado.ascenderA(new Rango("Soldado Profesional"), politica))
        .isInstanceOf(AscensoInvalidoException.class);
  }

  @Test
  void elHistorialExpuestoNoDebeSerModificableDesdeAfuera() {
    // Arrange
    Soldado soldado = new Soldado(ID_VALIDO, NOMBRE_VALIDO, "Soldado Profesional", COMPONENTE_VALIDO)
        .ascenderA(new Rango("Cabo Segundo"), new PoliticaAscensoMilitar());
    List<RegistroDeAscenso> historial = soldado.getHistorialDeAscensos();

    // Act
    // Assert
    assertThatThrownBy(() -> historial.add(null)).isInstanceOf(UnsupportedOperationException.class);
  }
}
