package com.jcaa.udec.soldados.domain.core.policy;

import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import java.util.List;

/**
 * Politica de dominio (regla de negocio variable/configurable) que define el orden jerarquico
 * oficial de los Rangos y, a partir de el, calcula a cuales Rangos puede ascender un Soldado que
 * hoy tiene un Rango determinado.
 *
 * <p>Se modela como una clase separada (y no como una constante dentro del Agregado) porque es
 * una regla que pertenece al negocio de "jerarquia militar" como tal, no al estado de un Soldado
 * individual: la misma politica aplica a todos los Soldados, puede cambiar si el Ministerio
 * reorganiza la jerarquia, y puede sustituirse (ej. una PoliticaAscensoMilitar distinta para
 * Oficiales vs Suboficiales) sin tocar la Entidad Soldado.
 */
public class PoliticaAscensoMilitar {

  private static final List<String> JERARQUIA_OFICIAL =
      List.of(
          "Soldado Profesional",
          "Cabo Segundo",
          "Cabo Primero",
          "Sargento Segundo",
          "Sargento Primero",
          "Sargento Viceprimero",
          "Sargento Mayor");

  /**
   * Devuelve los Rangos a los que un Soldado con el {@code rangoActual} puede ascender de forma
   * ESTANDAR, es decir, exactamente un nivel por encima en la jerarquia oficial.
   */
  public List<Rango> siguientesRangosValidos(Rango rangoActual) {
    int indiceActual = indiceDe(rangoActual);
    if (indiceActual < 0 || indiceActual == JERARQUIA_OFICIAL.size() - 1) {
      return List.of();
    }
    return List.of(new Rango(JERARQUIA_OFICIAL.get(indiceActual + 1)));
  }

  /**
   * Devuelve los Rangos a los que un Soldado puede ascender por MERITO: el siguiente nivel
   * estandar, mas un nivel adicional (saltando uno) cuando existe justificacion.
   */
  public List<Rango> siguientesRangosValidosPorMerito(Rango rangoActual) {
    int indiceActual = indiceDe(rangoActual);
    if (indiceActual < 0) {
      return List.of();
    }
    return JERARQUIA_OFICIAL.stream()
        .skip(indiceActual + 1L)
        .limit(2)
        .map(Rango::new)
        .toList();
  }

  public boolean esUltimoRango(Rango rango) {
    int indice = indiceDe(rango);
    return indice == JERARQUIA_OFICIAL.size() - 1;
  }

  private int indiceDe(Rango rango) {
    if (rango == null) {
      return -1;
    }
    return JERARQUIA_OFICIAL.indexOf(rango.valor());
  }
}
