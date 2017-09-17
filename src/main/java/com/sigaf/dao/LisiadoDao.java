/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ILisiadoDao;
import com.sigaf.pojo.TLisiado;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genovés
 */
public class LisiadoDao implements ILisiadoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TLisiado lisiado) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(lisiado);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TLisiado getLisiado(Integer id) {
        Session session = this.sessionFactory.openSession();
        TLisiado lisiado = (TLisiado) session.createQuery("from TLisiado l inner join fetch l.TCliente p where p.idCliente=:id").setParameter("id", id).uniqueResult();
        session.close();
        return lisiado;
    }

    @Override
    public List<TLisiado> listTLisiado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TLisiado lisiado) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(lisiado);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(TLisiado lisiado) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(lisiado);
        session.getTransaction().commit();
        session.close();
    }

}
