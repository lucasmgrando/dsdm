package com.example.demo.service.dto;

import com.example.demo.domain.Provincia;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;


public class ProvinciaDTO implements Serializable {

    private Long id;

    private String descripcion;


    private Set<ZonaDTO> zonas = new HashSet<>();

    private Long paisId;


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

    public Set<ZonaDTO> getZonas() {
        return zonas;
    }

    public void setZonas(Set<ZonaDTO> zonas) {
        this.zonas = zonas;
    }

    public Long getpaisId() {
        return paisId;
    }

    public void setPaisId(Long paisId) {
        this.paisId = paisId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Provincia)) {
            return false;
        }
        return id != null && id.equals(((Provincia) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Provincia{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}

