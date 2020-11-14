package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "provincia")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Provincia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "descripcion")
    private String descripcion;


    @OneToMany(mappedBy = "provincia")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Localidad> localidads = new HashSet<>();

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

	public Set<Localidad> getLocalidads() {
        return localidads;
    }

    public Provincia addLocalidad(Localidad localidad) {
        this.localidads.add(localidad);
        // localidad.setProvincia(this);
        return this;
    }

    public Provincia removeLocalidad(Localidad localidad) {
        this.localidads.remove(localidad);
        // localidad.setProvincia(null);
        return this;
    }

    public void setLocalidads(Set<Localidad> localidads) {
        this.localidads = localidads;
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

