/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IEjercicioDao;
import com.sigaf.pojo.TEjercicio;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class EjercicioDao implements IEjercicioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TEjercicio ejercicio) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ejercicio);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TEjercicio getEjercicio(Integer id) {

        Session session = this.sessionFactory.openSession();
        TEjercicio ejercicio = (TEjercicio) session.createQuery("from TEjercicio where idEjercicio  =:id").setParameter("id", id).uniqueResult();
        session.close();
        return ejercicio;
    }

    @Override
    public List<TEjercicio> listEjercicio(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TEjercicio> listEjercicios = session.createQuery("from TEjercicio where TEntidad.idEntidad  =:id").setParameter("id", id).list();
        session.close();
        return listEjercicios;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEjercicio ejercicio) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(ejercicio);
        session.getTransaction().commit();
        session.close();
    }

    public TEjercicio getEjercicioAbierto(Integer id) {

        Session session = this.sessionFactory.openSession();
        TEjercicio ejercicio = (TEjercicio) session.createQuery("from TEjercicio where TEntidad.idEntidad =:id and estadoEjercicio=true ").setParameter("id", id).uniqueResult();
        session.close();
        return ejercicio;
    }

}
