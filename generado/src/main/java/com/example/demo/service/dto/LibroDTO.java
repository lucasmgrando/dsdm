package com.example.demo.service.dto;

import com.example.demo.domain.Libro;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;


public class LibroDTO implements Serializable {

    private Long id;

    private String titulo;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Libro)) {
            return false;
        }
        return id != null && id.equals(((Libro) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Libro{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            "}";
    }
}

