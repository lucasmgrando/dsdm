package com.example.demo.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "libro")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "paginas")
    private int paginas;

    @Column(name = "autor")
    private String autor;

    @Column(name = "genero")
    private String genero;

    @Column(name = "fecha")
    private int fecha;


	@ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "libro_autor",
               joinColumns = @JoinColumn(name = "libro_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "autor_id", referencedColumnName = "id"))
    private Set<Autor> autor = new HashSet<>();


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

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = fecha;
    }

	public Set<Autor> getAutors() {
        return autors;
    }

    public Libro addAutor(Autor autor) {
        this.autors.add(autor);
        job.setLibro(this);
        return this;
    }

    public Libro removeAutor(Autor autor) {
        this.autors.remove(autor);
        job.setLibro(null);
        return this;
    }

    public void setAutors(Set<Autor> autors) {
        this.autors = autors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Libro)) {
            return false;
        }
        return id != null && id.equals(((Libro) o).id);
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
            ", paginas='" + getPaginas() + "'" +
            ", autor='" + getAutor() + "'" +
            ", genero='" + getGenero() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}

