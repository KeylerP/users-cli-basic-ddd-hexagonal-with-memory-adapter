package com.jcaa.udec.soldados.domain.core.factory;

import com.jcaa.udec.soldados.domain.core.event.SoldadoRegistradoEvent;
import com.jcaa.udec.soldados.domain.core.model.Soldado;
import java.time.Instant;

/**
 * Patron Factory: concentra la logica de creacion valida de un Soldado nuevo, que es mas que solo
 * invocar el constructor.
 *
 * <p>La regla de negocio que protege es: "todo Soldado que ingresa al sistema por esta via nace
 * en el Rango base 'Soldado Profesional'", sin importar que Rango le pase quien la invoque. Esto
 * evita que, por error o por un dato mal mapeado desde un adaptador, se pueda crear directamente
 * un Soldado ya en un Rango alto sin haber pasado por el proceso de ascenso que valida la
 * invariante (ver {@link Soldado#ascenderA}). Ademas, produce el {@link SoldadoRegistradoEvent}
 * correspondiente, para que el resto del sistema se entere del alta sin que el Agregado dependa
 * de un bus de eventos.
 */
public class SoldadoFactory {

  public static final String RANGO_BASE = "Soldado Profesional";

  public record SoldadoCreado(Soldado soldado, SoldadoRegistradoEvent evento) {}

  public SoldadoCreado crear(String id, String nombreCompleto, String componenteMilitar) {
    Soldado soldado = new Soldado(id, nombreCompleto, RANGO_BASE, componenteMilitar);
    SoldadoRegistradoEvent evento =
        new SoldadoRegistradoEvent(soldado.getId(), soldado.getNombreCompleto(), Instant.now());
    return new SoldadoCreado(soldado, evento);
  }
}
