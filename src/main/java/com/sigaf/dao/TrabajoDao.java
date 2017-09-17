/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ITrabajoDao;
import com.sigaf.pojo.TTrabajo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class TrabajoDao implements ITrabajoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TTrabajo trabajo) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(trabajo);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TTrabajo getTrabajo(Integer id) {

        Session session = this.sessionFactory.openSession();
        TTrabajo trabajo = (TTrabajo) session.createQuery("from TTrabajo where TCliente.idCliente  =:id").setParameter("id", id).uniqueResult();
        session.close();
        return trabajo;

    }

    @Override
    public List<TTrabajo> listTrabajo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TTrabajo trabajo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TTrabajo trabajo) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(trabajo);
        session.getTransaction().commit();
        session.close();
    }

}
