/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IDepreciacionDao;
import com.sigaf.pojo.TDepreciacion;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo Aguilar
 */
public class DepreciacionDao implements IDepreciacionDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TDepreciacion depreciacion) {
            
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(depreciacion);
        session.getTransaction().commit();
        session.close();   }

    @Override
    public TDepreciacion getDepreciacion(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TDepreciacion> listDepreciacion(Integer id) {
       
       Session session = this.sessionFactory.openSession();
        List<TDepreciacion> listaArea = session.createQuery("from TDepreciacion d where d.TActivoFijo.idActivoFijo =:id  ").setParameter("id", id).list();
        session.close();
        return listaArea;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TDepreciacion  depreciacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
