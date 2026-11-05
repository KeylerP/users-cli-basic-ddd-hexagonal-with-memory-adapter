package com.jcaa.udec.soldados.domain.core.model;

import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import java.time.Instant;

/**
 * Objeto interno del Agregado Soldado: un renglon inmutable del historial de ascensos. No tiene
 * identidad propia fuera del Soldado al que pertenece (no es una Entidad de primer nivel ni tiene
 * un repositorio propio), por eso vive encapsulado dentro de la Raiz de Agregado.
 */
public record RegistroDeAscenso(Rango rangoAnterior, Rango rangoNuevo, Instant fecha) {

  public RegistroDeAscenso {
    if (rangoAnterior == null || rangoNuevo == null) {
      throw new IllegalArgumentException("Un registro de ascenso requiere rango anterior y rango nuevo.");
    }
    if (fecha == null) {
      fecha = Instant.now();
    }
  }
}
