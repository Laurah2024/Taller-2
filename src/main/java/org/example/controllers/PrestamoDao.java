package org.example.controllers;

import org.example.connection.DatabaseConnection;
import org.example.entities.Prestamo;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class PrestamoDao {
    private DatabaseConnection databaseConnection;

    public PrestamoDao() {
        this.databaseConnection = new DatabaseConnection();
    }

    //metodo para guardar datos nuevos
    public void crearPrestamo(Prestamo prestamo) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.save(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para obtener los Prestamo por id
    public Prestamo obtenerPrestamoId(int id) {
        try (Session session = databaseConnection.getSession()) {
            return session.get(Prestamo.class, id);
        }
    }

    //metodo para obtener todas los Prestamo
    public List<Prestamo> obtenerPrestamo() {
        try (Session session = databaseConnection.getSession()) {
            return session.createQuery("FROM prestamo", Prestamo.class).list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para actualizar los prestamo
    public void actualizarPrestamo(Prestamo prestamo) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.update(prestamo);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para eliminar prestamo
    public void eliminarPrestamo(int id) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            Prestamo prestamo = session.get(Prestamo.class, id);
            if (prestamo!= null) {
                session.delete(prestamo);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}


