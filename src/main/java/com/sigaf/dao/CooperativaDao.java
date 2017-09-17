/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ICooperativaDao;
import com.sigaf.pojo.TCooperativa;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class CooperativaDao implements ICooperativaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TCooperativa coopertativa) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(coopertativa);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TCooperativa getTCooperativa(Integer id) {
        Session session = this.sessionFactory.openSession();
        TCooperativa cooperativa = (TCooperativa) session.createQuery("from TCooperativa where TProyecto.idproyecto =:id").setParameter("id", id).uniqueResult();
        session.close();
        return cooperativa;

    }

    @Override
    public List<TCooperativa> listTCooperativas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TCooperativa cooperativa) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(cooperativa);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(TCooperativa cooperativa) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(cooperativa);
        session.getTransaction().commit();
        session.close();

    }

}
