package com.example.demo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "domicilio")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Domicilio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero")
    private int numero;


    @OneToMany(mappedBy = "domicilio")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Localidad> localidads = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

	public Set<Localidad> getLocalidads() {
        return localidads;
    }

    public Domicilio addLocalidad(Localidad localidad) {
        this.localidads.add(localidad);
        job.setDomicilio(this);
        return this;
    }

    public Domicilio removeLocalidad(Localidad localidad) {
        this.localidads.remove(localidad);
        job.setDomicilio(null);
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
        if (!(o instanceof Domicilio)) {
            return false;
        }
        return id != null && id.equals(((Domicilio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Domicilio{" +
            "id=" + getId() +
            ", calle='" + getCalle() + "'" +
            ", numero='" + getNumero() + "'" +
            "}";
    }
}

