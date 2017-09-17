/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IAreaCultivoDao;
import com.sigaf.pojo.TAreaCultivo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class AreaCultivoDao implements IAreaCultivoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TAreaCultivo AreaCultivo) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(AreaCultivo);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TAreaCultivo getAreaCultivo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TAreaCultivo> listAreaCultivo() {

        List<TAreaCultivo> listaAreasCultivos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaAreasCultivos = session.createQuery("from TAreaCultivo").list();
        session.getTransaction().commit();
        session.close();
        return listaAreasCultivos;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TAreaCultivo AreaCultivo) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(AreaCultivo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean getTAreaCultivoNombre(String nombre) {

        Session session = this.sessionFactory.openSession();

        TAreaCultivo productor = (TAreaCultivo) session.createQuery("from TAreaCultivo where nombreAreaCultivo =:nombre").setParameter("nombre", nombre).uniqueResult();
        session.close();
        if (productor == null) {
            return false;
        } else {

            return true;
        }

    }

    @Override
    public List<TAreaCultivo> listAreaCultivoActivos() {

        List<TAreaCultivo> listaAreasCultivos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaAreasCultivos = session.createQuery("from TAreaCultivo where estadoAreaCultivo=true").list();
        session.getTransaction().commit();
        session.close();
        return listaAreasCultivos;

    }

}
