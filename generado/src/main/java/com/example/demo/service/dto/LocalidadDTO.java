package com.example.demo.service.dto;

import java.io.Serializable;

public class LocalidadDTO implements Serializable {

    private Long id;

    private String denominacion;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Localidad)) {
            return false;
        }
        return id != null && id.equals(((Localidad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Localidad{" +
            "id=" + getId() +
            ", denominacion='" + getDenominacion() + "'" +
            "}";
    }
}

