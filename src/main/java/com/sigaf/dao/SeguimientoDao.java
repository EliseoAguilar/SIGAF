/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ISeguimientoDao;
import com.sigaf.pojo.TSeguimiento;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genovés
 */
public class SeguimientoDao implements ISeguimientoDao {
    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    @Override
    public void create(TSeguimiento seguimiento) {
        seguimiento.setEstado(1);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(seguimiento);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TSeguimiento getSeguimiento(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TSeguimiento> listSeguimiento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TSeguimiento seguimiento) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(seguimiento);
        session.getTransaction().commit();
        session.close();
        
        
      }

    @Override
    public List<TSeguimiento> listaProyectoSeguimiento(int i) {
         List<TSeguimiento> listaProyectoSeguimiento = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaProyectoSeguimiento = session.createQuery("from TSeguimiento  where TProyecto.idproyecto =:i order by fechaseguimiento").setParameter("i", i).list();
        session.getTransaction().commit();
        session.close();
        return listaProyectoSeguimiento;
    }

    @Override
    public void delete(TSeguimiento seguimiento) {
  
     Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(seguimiento);
        session.getTransaction().commit();
        session.close();
    
    }
    
    
    
}
