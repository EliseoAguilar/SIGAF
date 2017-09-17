/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ICapacitacionDao;
import com.sigaf.pojo.TCapacitacion;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class CapacitacionDao implements ICapacitacionDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TCapacitacion capacitacion) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(capacitacion);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TCapacitacion getCapacitacion(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TCapacitacion> listCapacitacion() {
        List<TCapacitacion> listaCapacitacion = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaCapacitacion = session.createQuery("from TCapacitacion t inner join fetch t.TProyecto  inner join fetch t.TEmpleado").list();
        session.getTransaction().commit();
        session.close();
        return listaCapacitacion;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TCapacitacion capacitacion) {
    
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(capacitacion);
        session.getTransaction().commit();
        session.close();
    
    
    }

}
