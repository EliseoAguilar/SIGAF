/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IConfiguracionDao;
import com.sigaf.pojo.TConfiguracion;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class ConfiguracionDao implements IConfiguracionDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TConfiguracion configuracion) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(configuracion);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TConfiguracion getConfiguracion(Integer id) {

        Session session = this.sessionFactory.openSession();
        TConfiguracion configuracion = (TConfiguracion) session.createQuery("from TConfiguracion where TEntidad.idEntidad  =:id").setParameter("id", id).uniqueResult();
        session.close();
        return configuracion;

    }

    @Override
    public List<TConfiguracion> listConfiguracion(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TConfiguracion configuracion) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(configuracion);
        session.getTransaction().commit();
        session.close();
    }

}
