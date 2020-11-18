package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

@Audited
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


    @OneToMany(cascade=CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Zona> zonas = new HashSet<>();

    @ManyToOne(cascade=CascadeType.MERGE)
    @JsonIgnoreProperties(value = "provincias", allowSetters = true)
    private Pais pais;

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

	public Set<Zona> getZonas() {
        return zonas;
    }

    public Provincia addZona(Zona zona) {
        this.zonas.add(zona);
        // zona.setProvincia(this);
        return this;
    }

    public Provincia removeZona(Zona zona) {
        this.zonas.remove(zona);
        // zona.setProvincia(null);
        return this;
    }

    public void setZonas(Set<Zona> zonas) {
        this.zonas = zonas;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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

