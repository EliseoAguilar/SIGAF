/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IBajaActivoFijoDao;
import com.sigaf.pojo.TBajaActivoFijo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 *
 * @author Eliseo Aguilar
 */
public class BajaActivoFijoDao implements IBajaActivoFijoDao {

    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    
    @Override
    public void create(TBajaActivoFijo bajaActivoFijo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(bajaActivoFijo);
        session.getTransaction().commit();
        session.close();    }

    @Override
    public TBajaActivoFijo getTBajaActivoFijo(Integer id) {
        

   Session session = this.sessionFactory.openSession();
        TBajaActivoFijo bajaActivoFijo = (TBajaActivoFijo) session.createQuery("from TBajaActivoFijo  where TActivoFijo.idActivoFijo=:id").setParameter("id", id).uniqueResult();
        session.close();
        return bajaActivoFijo;    }

    @Override
    public List<TBajaActivoFijo> listBajaActivoFijo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TBajaActivoFijo bajaActivoFijo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
}
