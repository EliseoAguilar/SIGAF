/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IGarantiaProDao;
import com.sigaf.pojo.TGarantia;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genovés
 */
public class GarantiaProDao  implements IGarantiaProDao{
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
        TGarantia garantia = (TGarantia) session.createQuery("from TGarantia t inner join fetch  t.TProyecto p where p.idproyecto=:id").setParameter("id", id).uniqueResult();
        session.close();
        return garantia;
    }

    @Override
    public List<TGarantia> listTGarantia(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TGarantia garantia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
        
    
}
