package com.jcaa.udec.soldados.domain.core.model;

import com.jcaa.udec.soldados.domain.core.valueobject.ComponenteMilitar;
import com.jcaa.udec.soldados.domain.core.valueobject.NombreCompleto;
import com.jcaa.udec.soldados.domain.core.valueobject.Rango;
import com.jcaa.udec.soldados.domain.core.valueobject.SoldadoId;

public class Soldado {
    private final SoldadoId id;
    private final NombreCompleto nombreCompleto;
    private final Rango rango;
    private final ComponenteMilitar componenteMilitar;

    public Soldado(String id, String nombreCompleto, String rango, String componenteMilitar) {
        this.id = new SoldadoId(id);
        this.nombreCompleto = new NombreCompleto(nombreCompleto);
        this.rango = new Rango(rango);
        this.componenteMilitar = new ComponenteMilitar(componenteMilitar);
    }

    public String getId() {
        return id.valor();
    }

    public String getNombreCompleto() {
        return nombreCompleto.valor();
    }

    public String getRango() {
        return rango.valor();
    }

    public String getComponenteMilitar() {
        return componenteMilitar.valor();
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder manual (sin Lombok) para mantener en el mapper la misma
     * sintaxis fluida que se usa con Usuario.builder()...build().
     */
    public static final class Builder {
        private String id;
        private String nombreCompleto;
        private String rango;
        private String componenteMilitar;

        private Builder() {
        }

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
