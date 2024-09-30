package org.example.controllers;

import org.example.connection.DatabaseConnection;
import org.example.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class UsuarioDao {
    private DatabaseConnection databaseConnection;

    public UsuarioDao() {
        this.databaseConnection = new DatabaseConnection();
    }

    //metodo para guardar datos nuevos
    public void crearUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para obtener los Usuario por id
    public Usuario obtenerUsuarioId(int id) {
        try (Session session = databaseConnection.getSession()) {
            return session.get(Usuario.class, id);
        }
    }

    //metodo para obtener todas los Usuario
    public List<Usuario> obtenerUsuario() {
        try (Session session = databaseConnection.getSession()) {
            return session.createQuery("FROM usuario", Usuario.class).list();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    //metodo para actualizar los usuario
    public void actualizarUsuario(Usuario usuario) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //metodo para eliminar usuario
    public void eliminarUsuario(int id) {
        Transaction transaction = null;
        try (Session session = databaseConnection.getSession()) {
            transaction = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if (usuario!= null) {
                session.delete(usuario);
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

