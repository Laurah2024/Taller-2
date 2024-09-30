package org.example.controllers;

import org.example.connection.DatabaseConnection;
import org.example.entities.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class LibrosDao {
    private DatabaseConnection databaseConnection;

    public LibrosDao() {
        this.databaseConnection = new DatabaseConnection();
    }

    //metodo para guardar datos nuevos
    public void crearLibros(Libros libros) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.save(libros);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para obtener los libros por id
    public Libros obtenerLibrosId(int id) {
        try (Session session = databaseConnection.getSession()) {
            return session.get(Libros.class, id);
        }
    }

    //metodo para obtener todas los libros
    public List<Libros> obtenerLibros() {
        try (Session session = databaseConnection.getSession()) {
            return session.createQuery("FROM libros", Libros.class).list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para actualizar los libros
    public void actualizarLibros(Libros libros) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.update(libros);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para eliminar libros
    public void eliminarLibros(int id) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            Libros libros = session.get(Libros.class, id);
            if (libros != null) {
                session.delete(libros);
                System.out.println("Libro eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el libro con el ID proporcionado.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}


