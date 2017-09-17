/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IAsesoriaDao;
import com.sigaf.pojo.TAsesoria;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class AsesoriaDao implements IAsesoriaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TAsesoria asesoria) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(asesoria);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TAsesoria getAsesoria(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TAsesoria> listAsesoria() {

        List<TAsesoria> listaAsesoria = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaAsesoria = session.createQuery("from TAsesoria t inner join fetch t.TProyecto  inner join fetch t.TEmpleado").list();
        session.getTransaction().commit();
        session.close();
        return listaAsesoria;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TAsesoria asesoria) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(asesoria);
        session.getTransaction().commit();
        session.close();

    }

}
