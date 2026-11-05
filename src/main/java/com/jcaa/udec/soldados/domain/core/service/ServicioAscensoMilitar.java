package com.jcaa.udec.soldados.domain.core.service;

import com.jcaa.udec.soldados.domain.core.event.SoldadoAscendidoEvent;
import com.jcaa.udec.soldados.domain.core.exception.AscensoInvalidoException;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.specification.SoldadoElegibleParaAscensoSpecification;
import com.jcaa.udec.soldados.domain.core.strategy.EstrategiaValidacionAscenso;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import java.time.Instant;
import java.util.List;

/**
 * Servicio de Dominio: coordina el ascenso de un Soldado.
 *
 * <p>Se modela como Servicio de Dominio (y no como un metodo mas de la Entidad) porque la
 * operacion completa de "ascender a un soldado" necesita colaborar con conceptos que NO son
 * responsabilidad de un Soldado individual: la {@link PoliticaAscensoMilitar} (conocimiento de la
 * jerarquia completa, compartido por todos los Soldados) y la {@link
 * EstrategiaValidacionAscenso} elegida (una decision operativa externa: estandar o por merito).
 * Poner esta orquestacion dentro de Soldado obligaria a la Entidad a conocer politicas y
 * estrategias que no son su estado propio, rompiendo su cohesion.
 */
public class ServicioAscensoMilitar {

  public record ResultadoAscenso(Soldado soldadoActualizado, SoldadoAscendidoEvent evento) {}

  private final PoliticaAscensoMilitar politica;

  public ServicioAscensoMilitar(PoliticaAscensoMilitar politica) {
    this.politica = politica;
  }

  /** Contrato del Servicio de Dominio (ver pregunta 30 del informe para el pseudocodigo). */
  public ResultadoAscenso ascender(
      Soldado soldado, Rango rangoPropuesto, EstrategiaValidacionAscenso estrategia) {
    Rango rangoAnterior = soldado.getRango();

    SoldadoElegibleParaAscensoSpecification elegible =
        new SoldadoElegibleParaAscensoSpecification(rangoPropuesto, politica);
    List<Rango> candidatosSegunEstrategia = estrategia.rangosValidosPara(soldado, politica);
    boolean dentroDeLaEstrategia =
        candidatosSegunEstrategia.stream().anyMatch(r -> r.valor().equals(rangoPropuesto.valor()));

    if (!dentroDeLaEstrategia && !elegible.isSatisfiedBy(soldado)) {
      throw new AscensoInvalidoException(
          "El soldado "
              + soldado.getId().valor()
              + " no es elegible para ascender a "
              + rangoPropuesto.valor()
              + " con la estrategia aplicada.");
    }

    Soldado actualizado = soldado.ascenderA(rangoPropuesto, candidatosSegunEstrategia);
    SoldadoAscendidoEvent evento =
        new SoldadoAscendidoEvent(soldado.getId(), rangoAnterior, rangoPropuesto, Instant.now());

    return new ResultadoAscenso(actualizado, evento);
  }
}
