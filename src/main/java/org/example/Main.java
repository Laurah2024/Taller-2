package org.example;

import org.example.connection.DatabaseConnection;
import org.example.controllers.LibrosDao;
import org.example.controllers.PersonaDao;
import org.example.controllers.PrestamoDao;
import org.example.controllers.UsuarioDao;
import org.example.data.Data;
import org.example.entities.Libros;
import org.example.entities.Persona;
import org.example.entities.Prestamo;
import org.example.entities.Usuario;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseConnection dbconnect = new DatabaseConnection();
        dbconnect.connectDb();
        Data addData = new Data();
        addData.enterData();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los datos para crear la persona:");
        Persona persona = new Persona();

        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine();
        persona.setNombre(nombre);

        System.out.println("Ingrese el apellido:");
        String apellido = scanner.nextLine();
        persona.setApellido(apellido);

        System.out.println("Ingrese el sexo:");
        String sexo = scanner.nextLine();
        persona.setSexo(sexo);

        // Crear instancia del DAO y guardar la persona
        PersonaDao personaDao = new PersonaDao();
        personaDao.crearPersona(persona); // Método para guardar la persona en la base de datos

        System.out.println("Persona guardada exitosamente.");


        ///////////////////////////////////

        System.out.println("Ingrese los datos para crear el usuario:");
        Usuario usuario = new Usuario();

        System.out.println("Ingrese el rol");
        String rol = scanner.nextLine();
        usuario.setRol(rol);

        usuario.setIdPersona(persona);

        // Crear instancia del DAO y guardar la persona
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.crearUsuario(usuario); // Método para guardar el usuario en la base de datos

        System.out.println("Usuario guardada exitosamente.");

        ///////////////////////////////////

        System.out.println("Ingrese los datos para crear los libros:");
        Libros libros = new Libros();

        System.out.println("Ingrese el título");
        String titulo = scanner.nextLine();
        libros.setTitulo(titulo);

        System.out.println("Ingrese el autor de los libros");
        String autor = scanner.nextLine();
        libros.setAutor(autor);

        System.out.println("Ingrese el isb de los libros");
        String isbn = scanner.nextLine();
        libros.setIsbn(isbn);

        // Crear instancia del DAO y guardar los libros
        LibrosDao librosDao = new LibrosDao();
        librosDao.crearLibros(libros); // Método para guardar los libros en la base de datos

        System.out.println("Libros creados exitosamente.");

        //////

        // Actualizar libros.
        System.out.println("Ingrese el Id del libro a actualizar:");
        String librosIdStr = scanner.nextLine();
        int librosId = Integer.parseInt(librosIdStr); // Convertir el ID a int

// Obtener el libro desde la base de datos
        Libros librosActualizar = librosDao.obtenerLibrosId(librosId);

        if (librosActualizar != null) {
            // Modificar los atributos del libros
            System.out.println("Ingrese el nuevo título (deje en blanco si no desea cambiarlo):");
            String nuevoTitulo = scanner.nextLine();
            if (!nuevoTitulo.isEmpty()) {
                librosActualizar.setTitulo(nuevoTitulo);
            }

            System.out.println("Ingrese el nuevo autor (deje en blanco si no desea cambiarlo):");
            String nuevoAutor = scanner.nextLine();
            if (!nuevoAutor.isEmpty()) {
                librosActualizar.setAutor(nuevoAutor);
            }

            System.out.println("Ingrese el nuevo ISBN (deje en blanco si no desea cambiarlo):");
            String nuevoIsbn = scanner.nextLine();
            if (!nuevoIsbn.isEmpty()) {
                librosActualizar.setIsbn(nuevoIsbn);
            }

            // Guardar los cambios en la base de datos
            librosDao.actualizarLibros(librosActualizar); // Usa libroActualizar en lugar de libro

            System.out.println("Libro actualizado exitosamente.");
        } else {
            System.out.println("No se encontró el libro con el ID especificado.");
        }

// Eliminar libros.
        System.out.println("Ingrese el ID del libros a eliminar:");
        String librosIdStr2 = scanner.nextLine();
        int librosId2 = Integer.parseInt(librosIdStr2); // Convertir el ID a int

        LibrosDao librosDao2 = new LibrosDao();
        librosDao.eliminarLibros(librosId2);

        ///////////////////////////////////

        System.out.println("Ingrese los datos para el préstamo:");
        Prestamo prestamo = new Prestamo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Fecha del préstamo:");
        String fechaPrestamo = scanner.nextLine();
        prestamo.setFechaPrestamo(dateFormat.parse(fechaPrestamo));

        System.out.println("Ingrese la fecha de devolución:");
        String devolucion = scanner.nextLine();
        prestamo.setFechaDevolucion(dateFormat.parse(devolucion));

        prestamo.setIdUsuario(usuario);

        prestamo.setIdLibros(libros);

        // Crear instancia del DAO y guardar el libro
        PrestamoDao prestamoDao = new PrestamoDao();
        prestamoDao.crearPrestamo(prestamo); // Método para guardar el libro en la base de datos.

        System.out.println("Libro creado exitosamente.");

    }
}
