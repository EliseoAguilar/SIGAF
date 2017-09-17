/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ICompradorDao;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TComprador;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class CompradorDao implements ICompradorDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TComprador Comprador) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Comprador);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TComprador getComprador(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TComprador> listComprador() {

        List<TComprador> listaCompradores = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaCompradores = session.createQuery("from TComprador").list();
        session.getTransaction().commit();
        session.close();
        return listaCompradores;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TComprador Comprador) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(Comprador);
        session.getTransaction().commit();
        session.close();

    }
 
    @Override
    public boolean getTCompradorCodigo(String codigo) {
    
    Session session = this.sessionFactory.openSession();

        TComprador comprador = (TComprador) session.createQuery("from TComprador where duiComprador =:codigo").setParameter("codigo", codigo).uniqueResult();
        session.close();
        if (comprador == null) {
            return false;
        } else {

            return true;
        }
    
    }

}
