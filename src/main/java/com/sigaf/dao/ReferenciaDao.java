/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IReferenciaDao;
import com.sigaf.pojo.TReferencia;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ReferenciaDao implements IReferenciaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TReferencia Referencia) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Referencia);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TReferencia getReferencia(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TReferencia> listReferencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TReferencia Referencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TReferencia> listReferencia(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TReferencia> listReferencia = session.createQuery("from TReferencia where TProyecto.idproyecto  =:id").setParameter("id", id).list();
        session.close();
        return listReferencia;

    }

    @Override
    public void delete(TReferencia referencia) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(referencia);
        session.getTransaction().commit();
        session.close();

    }

}
