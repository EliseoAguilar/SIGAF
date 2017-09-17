/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IValorActivoDao;

import com.sigaf.pojo.TValorActivo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class ValorActivoDao  implements IValorActivoDao{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void create(TValorActivo valor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(valor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TValorActivo getTValorActivo(Integer id) {
        Session session = this.sessionFactory.openSession();
        TValorActivo valorActivo = (TValorActivo) session.createQuery("from  TValorActivo v  where v.idValorActivo =( select min( a.idValorActivo ) from TValorActivo a  where a.TActivoFijo.idActivoFijo=:id)").setParameter("id", id).uniqueResult();
        session.close();
        return valorActivo;
    }

    @Override
    public List<TValorActivo> listTValorActivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TValorActivo valor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(valor);
        session.getTransaction().commit();
        session.close();   
    }
    
}
