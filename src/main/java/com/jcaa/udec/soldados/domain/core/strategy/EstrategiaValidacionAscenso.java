package com.jcaa.udec.soldados.domain.core.strategy;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import java.util.List;

/**
 * Patron Strategy: la regla para decidir "a que rangos puede ascender este soldado" varia segun
 * el tipo de ascenso (estandar vs por merito), y esa variacion no debe resolverse con
 * condicionales dispersos en el Servicio de Dominio. Cada implementacion encapsula una forma de
 * calcular los rangos validos; el {@code ServicioAscensoMilitar} recibe la estrategia a usar y la
 * aplica sin saber cual es.
 */
public interface EstrategiaValidacionAscenso {

  List<Rango> rangosValidosPara(Soldado soldado, PoliticaAscensoMilitar politica);
}
