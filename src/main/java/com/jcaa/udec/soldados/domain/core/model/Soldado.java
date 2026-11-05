package com.jcaa.udec.soldados.domain.core.model;

import com.jcaa.udec.soldados.domain.core.exception.AscensoInvalidoException;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.ComponenteMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.NombreCompleto;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import com.jcaa.udec.soldados.domain.core.valueobject.SoldadoId;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entidad Soldado, Raiz de Agregado del contexto de Gestion de Personal Militar.
 *
 * <p>Es la Raiz de Agregado porque es el unico punto de entrada para modificar el historial de
 * ascensos interno ({@link RegistroDeAscenso}): nadie fuera de esta clase puede agregar un
 * registro directamente, solo a traves de {@link #ascenderA}, que es quien hace cumplir la
 * invariante de la jerarquia militar.
 */
public class Soldado {

  private final SoldadoId id;
  private final NombreCompleto nombreCompleto;
  private final Rango rango;
  private final ComponenteMilitar componenteMilitar;
  private final List<RegistroDeAscenso> historialDeAscensos;

  public Soldado(String id, String nombreCompleto, String rango, String componenteMilitar) {
    this(
        new SoldadoId(id),
        new NombreCompleto(nombreCompleto),
        new Rango(rango),
        new ComponenteMilitar(componenteMilitar),
        List.of());
  }

  private Soldado(
      SoldadoId id,
      NombreCompleto nombreCompleto,
      Rango rango,
      ComponenteMilitar componenteMilitar,
      List<RegistroDeAscenso> historialDeAscensos) {
    this.id = id;
    this.nombreCompleto = nombreCompleto;
    this.rango = rango;
    this.componenteMilitar = componenteMilitar;
    this.historialDeAscensos = List.copyOf(historialDeAscensos);
  }

  /**
   * Metodo de la Raiz de Agregado que hace cumplir la invariante: "el Rango de un Soldado solo
   * puede avanzar siguiendo el orden jerarquico oficial que define la {@link
   * PoliticaAscensoMilitar}; no se pueden saltar niveles ni retroceder".
   *
   * <p>Devuelve una NUEVA instancia de Soldado con el Rango actualizado y el historial ampliado
   * (el Agregado es tratado como inmutable hacia afuera), para no dejar nunca un Soldado en un
   * estado parcialmente actualizado.
   */
  public Soldado ascenderA(Rango nuevoRango, PoliticaAscensoMilitar politica) {
    return ascenderA(nuevoRango, politica.siguientesRangosValidos(this.rango));
  }

  /**
   * Variante del metodo anterior que recibe ya calculada la lista de rangos permitidos: permite
   * que un Servicio de Dominio decida, con la {@link
   * com.jcaa.udec.soldados.domain.core.strategy.EstrategiaValidacionAscenso} que corresponda,
   * CUALES rangos son candidatos validos (estandar vs por merito), mientras que esta Entidad
   * sigue siendo la unica responsable de aplicar el cambio de estado y de rechazar cualquier
   * transicion que no este en ese conjunto permitido.
   */
  public Soldado ascenderA(Rango nuevoRango, List<Rango> rangosPermitidos) {
    if (nuevoRango == null) {
      throw new AscensoInvalidoException("El nuevo rango no puede ser nulo.");
    }
    boolean esValido = rangosPermitidos.stream().anyMatch(r -> r.valor().equals(nuevoRango.valor()));
    if (!esValido) {
      throw new AscensoInvalidoException(
          "No se puede ascender al soldado "
              + this.id.valor()
              + " de "
              + this.rango.valor()
              + " a "
              + nuevoRango.valor()
              + ": viola el orden jerarquico oficial.");
    }

    List<RegistroDeAscenso> nuevoHistorial = new ArrayList<>(this.historialDeAscensos);
    nuevoHistorial.add(new RegistroDeAscenso(this.rango, nuevoRango, Instant.now()));

    return new Soldado(this.id, this.nombreCompleto, nuevoRango, this.componenteMilitar, nuevoHistorial);
  }

  public SoldadoId getId() {
    return id;
  }

  public NombreCompleto getNombreCompleto() {
    return nombreCompleto;
  }

  public Rango getRango() {
    return rango;
  }

  public ComponenteMilitar getComponenteMilitar() {
    return componenteMilitar;
  }

  public List<RegistroDeAscenso> getHistorialDeAscensos() {
    return Collections.unmodifiableList(historialDeAscensos);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String id;
    private String nombreCompleto;
    private String rango;
    private String componenteMilitar;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder nombreCompleto(String nombreCompleto) {
      this.nombreCompleto = nombreCompleto;
      return this;
    }

    public Builder rango(String rango) {
      this.rango = rango;
      return this;
    }

    public Builder componenteMilitar(String componenteMilitar) {
      this.componenteMilitar = componenteMilitar;
      return this;
    }

    public Soldado build() {
      return new Soldado(id, nombreCompleto, rango, componenteMilitar);
    }
  }
}
