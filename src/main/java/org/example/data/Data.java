package org.example.data;

import org.example.entities.Libros;
import org.example.entities.Persona;
import org.example.entities.Prestamo;
import org.example.entities.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Data {

    private SessionFactory sessionFactory;
    private boolean dataInit;

    public Data() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void enterData() throws ParseException {
        if (dataInit) {
            System.out.println("los datos ya han sido creados ya");
            return;
        }

        Session session = sessionFactory.openSession();
        try {
            long countPersonas = (long) session.createQuery("SELECT COUNT(p.id) FROM Persona p").uniqueResult();
            if (countPersonas > 0) {
                System.out.println("los datos ya existen en la base de datos");
                dataInit = true;
                session.close();
                return;

            }
            session.beginTransaction();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-mm-dd");

            //persona
            Persona persona1 = new Persona();
            persona1.setNombre("Laura");
            persona1.setApellido("Hoyos");
            persona1.setSexo("Femenino");

            Persona persona2 = new Persona();
            persona2.setNombre("Salome");
            persona2.setApellido("Castaño");
            persona2.setSexo("Femenino");

            Persona persona3 = new Persona();
            persona3.setNombre("Carolina");
            persona3.setApellido("Sanchez");
            persona3.setSexo("Femenino");

            Persona persona4 = new Persona();
            persona4.setNombre("Estefanie");
            persona4.setApellido("Gomez");
            persona4.setSexo("Femenino");

            Persona persona5 = new Persona();
            persona5.setNombre("Esteban");
            persona5.setApellido("Buitrago");
            persona5.setSexo("Masculino");

            //usuario
            Usuario usuario1 = new Usuario();
            usuario1.setIdPersona(persona1);
            usuario1.setRol("Bibliotecario");

            Usuario usuario2 = new Usuario();
            usuario1.setIdPersona(persona2);
            usuario1.setRol("Bibliotecario");

            Usuario usuario3 = new Usuario();
            usuario1.setIdPersona(persona3);
            usuario1.setRol("Bibliotecario");

            Usuario usuario4 = new Usuario();
            usuario4.setIdPersona(persona4);
            usuario4.setRol("Bibliotecario");

            Usuario usuario5 = new Usuario();
            usuario5.setIdPersona(persona5);
            usuario5.setRol("Cliente");

            //libros
            Libros libros1 = new Libros();
            libros1.setAutor("Laura Maria Hoyos Castañeda");
            libros1.setTitulo("Como aprender a programar");
            libros1.setIsbn("23");

            Libros libros2 = new Libros();
            libros2.setAutor("Gabriel García Máquez");
            libros2.setTitulo("Crónica de una muerte anunciada");
            libros2.setIsbn("27");

            Libros libros3 = new Libros();
            libros3.setAutor("Pablo Neruda");
            libros3.setTitulo("Cien sonetos de amor");
            libros3.setIsbn("29");

            Libros libros4 = new Libros();
            libros4.setAutor("Mario Benedetti");
            libros4.setTitulo("La borra del café");
            libros4.setIsbn("31");

            Libros libros5 = new Libros();
            libros5.setAutor("Gregory Mankiw");
            libros5.setTitulo("Macroeconomía");
            libros5.setIsbn("33");

            //prestamo
            Prestamo prestamo1 = new Prestamo();
            prestamo1.setIdUsuario(usuario1);
            prestamo1.setIdLibros(libros1);
            prestamo1.setFechaPrestamo(formato.parse("2024-09-29"));
            prestamo1.setFechaDevolucion(formato.parse("0000-00-00"));
            prestamo1.setActivo(true);

            Prestamo prestamo2 = new Prestamo();
            prestamo2.setIdUsuario(usuario2);
            prestamo2.setIdLibros(libros2);
            prestamo2.setFechaPrestamo(formato.parse("2024-09-29"));
            prestamo2.setFechaDevolucion(formato.parse("0000-00-00"));
            prestamo1.setActivo(true);

            Prestamo prestamo3 = new Prestamo();
            prestamo3.setIdUsuario(usuario3);
            prestamo3.setIdLibros(libros3);
            prestamo3.setFechaPrestamo(formato.parse("2024-10-27"));
            prestamo3.setFechaDevolucion(formato.parse("0000-00-00"));
            prestamo1.setActivo(true);

            Prestamo prestamo4 = new Prestamo();
            prestamo4.setIdUsuario(usuario4);
            prestamo4.setIdLibros(libros4);
            prestamo4.setFechaPrestamo(formato.parse("2024-11-02"));
            prestamo4.setFechaDevolucion(formato.parse("0000-00-00"));
            prestamo1.setActivo(true);

            Prestamo prestamo5 = new Prestamo();
            prestamo5.setIdUsuario(usuario5);
            prestamo5.setIdLibros(libros5);
            prestamo5.setFechaPrestamo(formato.parse("2024-12-23"));
            prestamo5.setFechaDevolucion(formato.parse("0000-00-00"));
            prestamo1.setActivo(true);

            List<Object> entities = Arrays.asList(persona1, persona2, persona3, persona4, persona5, usuario1, usuario2, usuario3, usuario4, usuario5, libros1, libros2, libros3, libros4, libros5, prestamo1, prestamo2, prestamo3, prestamo4, prestamo5);

            for (Object entity : entities) {
                session.persist(entity);
            }
            session.getTransaction().commit();
            dataInit = true;
            System.out.println("los datos han sido creados correctamente");

        } catch (HibernateException error) {
            System.out.println("el error es" + error.getMessage());
        } finally {
            session.close();
        }
    }
}