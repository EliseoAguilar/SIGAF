/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IAgropecuarioDao;
import com.sigaf.pojo.TAgropecuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class AgropecuarioDao implements IAgropecuarioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TAgropecuario Agropecuario) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Agropecuario);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TAgropecuario getAgropecuario(Integer id) {
    
     Session session = this.sessionFactory.openSession();
        TAgropecuario agropecuario =  (TAgropecuario) session.createQuery("from TAgropecuario where TProyecto.idproyecto =:id").setParameter("id", id).uniqueResult();
        session.close();
        return agropecuario;
    
    }

    @Override
    public List<TAgropecuario> listAgropecuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void update(TAgropecuario Agropecuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TAgropecuario agropecuario) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(agropecuario);
        session.getTransaction().commit();
        session.close();
    
    
    }

}
