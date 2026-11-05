package com.jcaa.udec.soldados.domain.core.event;

import com.jcaa.udec.soldados.domain.core.valueobject.NombreCompleto;
import com.jcaa.udec.soldados.domain.core.valueobject.SoldadoId;
import java.time.Instant;

/** Evento de Dominio: un nuevo Soldado quedo registrado en el sistema. */
public record SoldadoRegistradoEvent(SoldadoId soldadoId, NombreCompleto nombreCompleto, Instant ocurridoEn)
    implements DomainEvent {

  public SoldadoRegistradoEvent {
    if (soldadoId == null || nombreCompleto == null) {
      throw new IllegalArgumentException("El evento SoldadoRegistradoEvent requiere soldadoId y nombreCompleto.");
    }
    if (ocurridoEn == null) {
      ocurridoEn = Instant.now();
    }
  }
}
