package com.jcaa.udec.soldados.domain.core.event;

import java.time.Instant;

/**
 * Marca comun para todos los Eventos de Dominio del contexto de Soldados.
 *
 * <p>Un Evento de Dominio representa un hecho de negocio YA OCURRIDO (se nombra en pasado) que le
 * interesa comunicar al resto del sistema (u otros Bounded Context), sin que el dominio dependa
 * de quien lo escuche.
 */
public interface DomainEvent {

  Instant ocurridoEn();
}
