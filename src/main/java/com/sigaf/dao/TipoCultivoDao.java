/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ITipoCultivoDao;
import com.sigaf.pojo.TTipoCultivo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class TipoCultivoDao implements ITipoCultivoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TTipoCultivo TipoCultivo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(TipoCultivo);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TTipoCultivo getAreaCultivo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TTipoCultivo> listTipoCultivo() {

        List<TTipoCultivo> listaTiposCultivos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaTiposCultivos = session.createQuery("from TTipoCultivo t inner join fetch t.TAreaCultivo").list();
        session.getTransaction().commit();
        session.close();
        return listaTiposCultivos;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TTipoCultivo TipoCultivo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(TipoCultivo);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public boolean getTTipoCultivoNombre(String nombre) {
    Session session = this.sessionFactory.openSession();

        TTipoCultivo productor = (TTipoCultivo) session.createQuery("from TTipoCultivo where nombreTipoCultivo =:nombre").setParameter("nombre", nombre).uniqueResult();
        session.close();
        if (productor == null) {
            return false;
        } else {

            return true;
        }
    
    

    
    
    }

}
