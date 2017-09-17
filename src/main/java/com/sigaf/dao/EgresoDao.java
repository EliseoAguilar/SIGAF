/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IEgresoDao;
import com.sigaf.pojo.TEgreso;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class EgresoDao implements IEgresoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TEgreso Egreso) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Egreso);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TEgreso getEgreso(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TEgreso> listEgreso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEgreso Egreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TEgreso> listEgreso(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TEgreso> listEgreso = session.createQuery("from TEgreso where TProyecto.idproyecto=:id").setParameter("id", id).list();
        session.close();
        return listEgreso;

    }

    @Override
    public void delete(TEgreso egreso) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(egreso);
        session.getTransaction().commit();
        session.close();

    }

}
