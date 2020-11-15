package com.example.demo.service.dto;

import com.example.demo.domain.Pais;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;


public class PaisDTO implements Serializable {

    private Long id;

    private String descripcion;



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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pais)) {
            return false;
        }
        return id != null && id.equals(((Pais) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Pais{" +
            "id=" + getId() +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }
}

