/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IGarantiaDao;
import com.sigaf.pojo.TGarantia;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class GarantiaDao implements IGarantiaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TGarantia garantia) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(garantia);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TGarantia getTGarantia(Integer id) {

        Session session = this.sessionFactory.openSession();
        TGarantia garantia = (TGarantia) session.createQuery("from TGarantia where TProyecto.idproyecto =:id").setParameter("id", id).uniqueResult();
        session.close();
        return garantia;

    }

    @Override
    public List<TGarantia> listTGarantia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public void update(TGarantia entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TGarantia> listTGarantia(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TGarantia> listGarantia = session.createQuery("from TGarantia where TProyecto.idproyecto  =:id").setParameter("id", id).list();
        session.close();
        return listGarantia;

    }

    @Override
    public void delete(TGarantia garantia) {
   
     Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(garantia);
        session.getTransaction().commit();
        session.close();

    
    }

}
