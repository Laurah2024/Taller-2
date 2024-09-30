package org.example.interfaces;

import org.example.entities.Usuario;

import java.util.Date;

public interface Prestamo {
    Usuario getIdUsuario();
    void setIdUsuario(Usuario idUsuario);

    Libros getIdLibros();
    void setIdLibros(org.example.entities.Libros idlibros);

    Date getFechaPrestamo();
    void setFechaPrestamo(Date fechaPrestamo);

    Date getFechaDevolucion();
    void setFechaDevolucion(Date fechaDevolucion);

    boolean isActivo();
    void setActivo(boolean activo);
}
