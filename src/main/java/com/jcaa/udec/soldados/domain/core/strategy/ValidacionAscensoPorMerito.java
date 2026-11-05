package com.jcaa.udec.soldados.domain.core.strategy;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import java.util.List;

/**
 * Estrategia alterna: permite ademas un ascenso adicional (saltar un nivel extra) cuando el
 * ascenso se justifica por merito (ej. una condecoracion registrada), siguiendo igual el orden de
 * la jerarquia (no permite elegir cualquier rango, solo ampliar en uno el rango de alcance).
 */
public class ValidacionAscensoPorMerito implements EstrategiaValidacionAscenso {

  @Override
  public List<Rango> rangosValidosPara(Soldado soldado, PoliticaAscensoMilitar politica) {
    return politica.siguientesRangosValidosPorMerito(soldado.getRango());
  }
}
