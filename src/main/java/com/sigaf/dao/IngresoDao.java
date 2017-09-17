/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IIngresoDao;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TIngreso;
import com.sigaf.pojo.TProveedor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class IngresoDao implements IIngresoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TIngreso Ingreso) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Ingreso);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TIngreso getIngreso(Integer id) {

        Session session = this.sessionFactory.openSession();
        TIngreso ingreso =  (TIngreso) session.createQuery("from TIngreso where TProyecto.idproyecto=:id").setParameter("id", id).uniqueResult();
        session.close();
        return ingreso;

    }

    @Override
    public List<TIngreso> listIngreso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public void update(TIngreso Ingreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TIngreso> listIngreso(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TIngreso> listIngreso = session.createQuery("from TIngreso where TProyecto.idproyecto  =:id").setParameter("id", id).list();
        session.close();
        return listIngreso;

    }

    @Override
    public void delete(TIngreso ingreso) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(ingreso);
        session.getTransaction().commit();
        session.close();
    
    
    }

}
