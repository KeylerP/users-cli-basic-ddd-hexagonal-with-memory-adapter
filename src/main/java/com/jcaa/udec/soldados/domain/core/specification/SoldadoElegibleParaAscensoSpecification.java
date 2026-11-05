package com.jcaa.udec.soldados.domain.core.specification;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;

/**
 * Specification concreta: responde si un {@link Soldado} es elegible para ascender a un {@code
 * rangoPropuesto} especifico, combinando dos criterios de negocio:
 *
 * <ol>
 *   <li>El Soldado no esta ya en el ultimo Rango de la jerarquia (no hay a donde ascender).
 *   <li>El {@code rangoPropuesto} esta entre los siguientes Rangos validos segun la {@link
 *       PoliticaAscensoMilitar}.
 * </ol>
 *
 * <p>Se separa de la Politica a proposito: la Politica calcula CUALES son los siguientes rangos
 * validos (una regla general sobre la jerarquia), mientras que la Specification responde una
 * pregunta puntual de SI/NO sobre un candidato concreto, y se puede combinar con otras reglas
 * (ej. "y ademas pertenece a un componente activo") usando and()/or()/not() sin tocar esta clase.
 */
public class SoldadoElegibleParaAscensoSpecification implements Specification<Soldado> {

  private final Rango rangoPropuesto;
  private final PoliticaAscensoMilitar politica;

  public SoldadoElegibleParaAscensoSpecification(Rango rangoPropuesto, PoliticaAscensoMilitar politica) {
    this.rangoPropuesto = rangoPropuesto;
    this.politica = politica;
  }

  @Override
  public boolean isSatisfiedBy(Soldado soldado) {
    if (soldado == null || rangoPropuesto == null) {
      return false;
    }
    if (politica.esUltimoRango(soldado.getRango())) {
      return false;
    }
    return politica.siguientesRangosValidos(soldado.getRango()).stream()
        .anyMatch(r -> r.valor().equals(rangoPropuesto.valor()));
  }
}
