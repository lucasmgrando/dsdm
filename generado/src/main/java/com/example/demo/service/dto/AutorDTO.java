package com.example.demo.service.dto;

import com.example.demo.domain.Autor;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;


public class AutorDTO implements Serializable {

    private Long id;

    private String nombre;


    private DomicilioDTO domicilio;

    private Set<LibroDTO> libros = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DomicilioDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDTO domicilio) {
        this.domicilio = domicilio;
    }
    public Set<LibroDTO> getLibros() {
        return libros;
    }

    public void setLibros(Set<LibroDTO> libros) {
        this.libros = libros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Autor)) {
            return false;
        }
        return id != null && id.equals(((Autor) o).getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Autor{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}

