package com.jcaa.udec.soldados.domain.core.event;

import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import com.jcaa.udec.soldados.domain.core.valueobject.SoldadoId;
import java.time.Instant;

/** Evento de Dominio: un Soldado fue ascendido de un Rango a otro. */
public record SoldadoAscendidoEvent(
    SoldadoId soldadoId, Rango rangoAnterior, Rango rangoNuevo, Instant ocurridoEn)
    implements DomainEvent {

  public SoldadoAscendidoEvent {
    if (soldadoId == null || rangoAnterior == null || rangoNuevo == null) {
      throw new IllegalArgumentException(
          "El evento SoldadoAscendidoEvent requiere soldadoId, rangoAnterior y rangoNuevo.");
    }
    if (ocurridoEn == null) {
      ocurridoEn = Instant.now();
    }
  }
}
