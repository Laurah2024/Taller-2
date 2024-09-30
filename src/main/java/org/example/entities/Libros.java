package org.example.entities;

import javax.persistence.*;

@Entity
@Table(name="Libros")
public class Libros implements org.example.interfaces.Libros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String autor;
    private String titulo;
    private String isbn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
