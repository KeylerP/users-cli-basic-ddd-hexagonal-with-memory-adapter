package com.jcaa.udec.soldados.domain.core.strategy;

import com.jcaa.udec.soldados.domain.core.model.Soldado;
import com.jcaa.udec.soldados.domain.core.policy.PoliticaAscensoMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import java.util.List;

/** Estrategia por defecto: solo permite ascender exactamente un nivel en la jerarquia oficial. */
public class ValidacionAscensoEstandar implements EstrategiaValidacionAscenso {

  @Override
  public List<Rango> rangosValidosPara(Soldado soldado, PoliticaAscensoMilitar politica) {
    return politica.siguientesRangosValidos(soldado.getRango());
  }
}
