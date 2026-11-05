package com.jcaa.udec.soldados.domain.core.specification;

/**
 * Contrato generico del patron Specification: una regla de negocio que un objeto del dominio
 * puede o no cumplir, que se puede evaluar de forma aislada y combinar con otras (and/or/not)
 * sin ensuciar la Entidad ni el Servicio de Dominio que la usa.
 */
public interface Specification<T> {

  boolean isSatisfiedBy(T candidato);

  default Specification<T> and(Specification<T> otra) {
    return candidato -> this.isSatisfiedBy(candidato) && otra.isSatisfiedBy(candidato);
  }

  default Specification<T> or(Specification<T> otra) {
    return candidato -> this.isSatisfiedBy(candidato) || otra.isSatisfiedBy(candidato);
  }

  default Specification<T> not() {
    return candidato -> !this.isSatisfiedBy(candidato);
  }
}
