package com.jcaa.udec.soldados.domain.core.exception;

/**
 * Excepcion de dominio critica del CEA.
 *
 * <p>Se lanza cuando se intenta llevar a un Soldado a un Rango que viola el orden jerarquico
 * oficial (saltar niveles, "descender" de grado, o ascender a un Rango que no existe en la
 * jerarquia). Protege la invariante mas importante del Agregado Soldado: la cadena de mando
 * militar solo puede avanzar siguiendo el orden que define la politica de ascenso.
 */
public class AscensoInvalidoException extends RuntimeException {

  public AscensoInvalidoException(String mensaje) {
    super(mensaje);
  }
}
