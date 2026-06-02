package com.jcaa.udec;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
  private static final String MENSAJE_INICIO_DOMAIN = "Capa de Dominio normalizada con DDD y SOLID";
  private static final String MENSAJE_INICIO_APPLICATION =
      "Capa de Aplicación normalizada con SOLID, DTO y Casos de uso";

  public static void main(String[] args) {
    log.info(MENSAJE_INICIO_DOMAIN);
    log.info(MENSAJE_INICIO_APPLICATION);
  }
}
