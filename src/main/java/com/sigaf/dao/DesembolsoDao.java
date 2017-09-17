/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IDesembolsoDao;
import com.sigaf.pojo.TDesembolso;
import com.sigaf.pojo.TEntidad;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class DesembolsoDao implements IDesembolsoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TDesembolso desembolso) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(desembolso);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TDesembolso getDesembolso(Integer id) {

        Session session = this.sessionFactory.openSession();
        TDesembolso desembolso = (TDesembolso) session.createQuery("from TDesembolso where idDesembolso in (select max(idDesembolso) from TDesembolso where TProyecto.idproyecto=:id) ").setParameter("id", id).uniqueResult();
        session.close();
        return desembolso;

    }

    @Override

    public List<TDesembolso> listDesembolso() {

        List<TDesembolso> listaDesembolsos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaDesembolsos = session.createQuery("from TDesembolso").list();
        session.getTransaction().commit();
        session.close();
        return listaDesembolsos;

    }

 

    @Override
    public void update(TDesembolso desembolso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TDesembolso> listDesembolso(Integer id) {

        List<TDesembolso> listaDesembolsos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaDesembolsos = session.createQuery("from TDesembolso where TProyecto.idproyecto=:id").setParameter("id", id).list();
        session.getTransaction().commit();
        session.close();
        return listaDesembolsos;

    }

    @Override
    public void delete(TDesembolso desembolso) {
       
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(desembolso);
        session.getTransaction().commit();
        session.close();
    
    }

}
