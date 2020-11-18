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
@Table(name = "autor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Autor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nombre")
    private String nombre;


	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(unique = true)
    private Domicilio domicilio;

	@ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "autor_libro",
               joinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"))
    private Set<Libro> libros = new HashSet<>();

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

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

	public Set<Libro> getLibros() {
        return libros;
    }

    public Autor addLibro(Libro libro) {
        this.libros.add(libro);
        // libro.setAutor(this);
        return this;
    }

    public Autor removeLibro(Libro libro) {
        this.libros.remove(libro);
        // libro.setAutor(null);
        return this;
    }

    public void setLibros(Set<Libro> libros) {
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

