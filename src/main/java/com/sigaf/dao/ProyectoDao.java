/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IProyectoDao;
import com.sigaf.pojo.TProyecto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ProyectoDao implements IProyectoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TProyecto proyecto) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(proyecto);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TProyecto getTProyecto(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProyecto> listTProyecto() {

        List<TProyecto> listaProyectos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaProyectos = session.createQuery("from TProyecto").list();
        session.getTransaction().commit();
        session.close();
        return listaProyectos;

    }

    @Override
    public void delete(TProyecto proyecto) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(proyecto);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void update(TProyecto proyecto) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(proyecto);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public Integer getCorrelativo() {

        Session session = this.sessionFactory.openSession();
        TProyecto nuevo = (TProyecto) session.createQuery("from TProyecto where idproyecto in (select max(idproyecto) from TProyecto)").uniqueResult();
        session.close();

        if (nuevo == null) {
            return 0;

        } else {
            return (nuevo.getIdproyecto());
        }

    }

    @Override
    public Integer getCorrelativoProyecto() {

        Session session = this.sessionFactory.openSession();
        TProyecto nuevo = (TProyecto) session.createQuery("from TProyecto where idproyecto in (select max(idproyecto) from TProyecto where estado=3)").uniqueResult();
        session.close();

        if (nuevo == null) {
            return 0;

        } else {
            return (nuevo.getIdproyecto());
        }

    }

    @Override
    public List<TProyecto> listTProyectoAprobados() {

        List<TProyecto> listaProyectos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaProyectos = session.createQuery("from TProyecto where estado=3 OR estado=5 OR estado=6").list();
        session.getTransaction().commit();
        session.close();
        return listaProyectos;

    }

    @Override
    public List<TProyecto> listTProyecto(Integer id) {

        List<TProyecto> listaProyectos = null;

        if (id == 1) {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            listaProyectos = session.createQuery("from TProyecto where estado=1 order by fecha").list();
            session.getTransaction().commit();
            session.close();
            return listaProyectos;

        } else if (id == 2) {

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            listaProyectos = session.createQuery("from TProyecto where estado=2 order by fecha DESC").list();
            session.getTransaction().commit();
            session.close();
            return listaProyectos;

        } else {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            listaProyectos = session.createQuery("from TProyecto where estado=3 OR estado=5 OR estado=6 or estado=7 order by estado DESC").list();
            session.getTransaction().commit();
            session.close();
            return listaProyectos;

        }

    }

    @Override
    public List<TProyecto> listTCreditos() {

        List<TProyecto> listaProyectos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaProyectos = session.createQuery("from TProyecto where codigoProyecto!=''").list();
        session.getTransaction().commit();
        session.close();
        return listaProyectos;

    }
}
