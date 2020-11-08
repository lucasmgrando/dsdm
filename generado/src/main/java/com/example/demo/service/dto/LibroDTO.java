package com.example.demo.service.dto;

import java.io.Serializable;

public class Libro implements Serializable {

    private Long id;

    private String titulo;

    private int paginas;

    private String autor;

    private String genero;

    private int fecha;



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
            ", autorId='" + getAutorId() + "'" +
            "}";
    }
}

