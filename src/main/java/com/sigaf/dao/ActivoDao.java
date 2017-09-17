/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IActivoDao;
import com.sigaf.pojo.TActivo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ActivoDao implements IActivoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TActivo Activo) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Activo);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TActivo getActivo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TActivo> listActivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    @Override
    public void update(TActivo Activo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TActivo> listActivo(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TActivo> listActivo = session.createQuery("from TActivo where TProyecto.idproyecto  =:id").setParameter("id", id).list();
        session.close();
        return listActivo;

    }

    @Override
    public void delete(TActivo activo) {
    
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(activo);
        session.getTransaction().commit();
        session.close();
    
    }

}
