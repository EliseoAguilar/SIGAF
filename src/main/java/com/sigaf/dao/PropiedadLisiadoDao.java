/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IPropiedadLisiadoDao;
import com.sigaf.pojo.TPropiedadLisiado;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genovés
 */
public class PropiedadLisiadoDao implements IPropiedadLisiadoDao{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void create(TPropiedadLisiado propiedad) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(propiedad);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TPropiedadLisiado getTPropiedadLisiado(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TPropiedadLisiado> listTPropiedadLisiado(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TPropiedadLisiado> listaPropiedadLisiado =  session.createQuery("from TPropiedadLisiado l inner join fetch l.TLisiado t  inner join fetch  t.TCliente p where p.idCliente=:id").setParameter("id", id).list();
        session.close();
        return listaPropiedadLisiado;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TPropiedadLisiado propiedad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
