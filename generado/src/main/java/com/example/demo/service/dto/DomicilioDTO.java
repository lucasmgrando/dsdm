package com.example.demo.service.dto;

import com.example.demo.domain.Domicilio;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;


public class DomicilioDTO implements Serializable {

    private Long id;

    private String descripcion;


    private LocalidadDTO localidad;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalidadDTO getLocalidad() {
        return localidad;
    }

    public void setLocalidad(LocalidadDTO localidad) {
        this.localidad = localidad;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Domicilio)) {
            return false;
        }
        return id != null && id.equals(((Domicilio) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}

