package com.example.demo.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

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

