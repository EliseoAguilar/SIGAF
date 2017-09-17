/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IComercioDao;
import com.sigaf.pojo.TComercio;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ComercioDao implements IComercioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TComercio comercio) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(comercio);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TComercio getComercio(Integer id) {

        Session session = this.sessionFactory.openSession();
        TComercio comercio = (TComercio) session.createQuery("from TComercio where TProyecto.idproyecto  =:id").setParameter("id", id).uniqueResult();
        session.close();
        return comercio;

    }

    @Override
    public List<TComercio> listComercio() {

        List<TComercio> listaComercio = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaComercio = session.createQuery("from TComercio").list();
        session.getTransaction().commit();
        session.close();
        return listaComercio;

    }

    @Override
    public void update(TComercio comercio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TComercio comercio) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(comercio);
        session.getTransaction().commit();
        session.close();

    }

}
