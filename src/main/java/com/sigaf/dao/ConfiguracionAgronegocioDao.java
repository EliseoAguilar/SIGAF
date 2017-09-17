/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IConfiguracionAgronegocioDao;
import com.sigaf.pojo.TConfiguracionAgronegocio;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ConfiguracionAgronegocioDao implements IConfiguracionAgronegocioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TConfiguracionAgronegocio Configuracion) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Configuracion);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TConfiguracionAgronegocio getConfiguracion(Integer id) {

        if (id == 0) {

            Session session = this.sessionFactory.openSession();
            TConfiguracionAgronegocio configuracion = (TConfiguracionAgronegocio) session.createQuery("from TConfiguracionAgronegocio where estadoConfiguracion=true").uniqueResult();
            session.close();
            return configuracion;    

        } else {

            Session session = this.sessionFactory.openSession();
            TConfiguracionAgronegocio configuracion = (TConfiguracionAgronegocio) session.createQuery("from TConfiguracionAgronegocio where idConfiguracionAgronegocio=:id").setParameter("id", id).uniqueResult();
            session.close();
            return configuracion;

        }

    }

    @Override
    public List<TConfiguracionAgronegocio> listConfiguracion() {

        List<TConfiguracionAgronegocio> listaAreasCultivos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaAreasCultivos = session.createQuery("from TConfiguracionAgronegocio").list();
        session.getTransaction().commit();
        session.close();
        return listaAreasCultivos;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TConfiguracionAgronegocio Configuracion) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(Configuracion);
        session.getTransaction().commit();
        session.close();

    }

}
