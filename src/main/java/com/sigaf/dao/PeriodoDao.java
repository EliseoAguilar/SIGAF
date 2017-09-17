/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IPeriodoDao;
import com.sigaf.pojo.TPeriodo;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class PeriodoDao  implements IPeriodoDao{
   
    
    private SessionFactory sessionFactory;
    

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void create(TPeriodo periodo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        /*Query q=session.createSQLQuery("update  t_periodo set estado_periodo = false where id_ejercicio =:id").setParameter("id", periodo.getTEjercicio().getIdEjercicio());
        q.executeUpdate();*/
        session.save(periodo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TPeriodo getPeriodo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TPeriodo periodo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        /*Query q=session.createSQLQuery("update  t_periodo set estado_periodo = false where id_ejercicio =:id").setParameter("id", periodo.getTEjercicio().getIdEjercicio());
        q.executeUpdate();*/
        session.update(periodo);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<TPeriodo> listPeriodo(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TPeriodo> listPeriodos = session.createQuery("from TPeriodo where TEjercicio.idEjercicio=:id order by idPeriodo DESC").setParameter("id", id).list();
        session.close();
      
        return listPeriodos; 
    }
    
    

       
    
    @Override
    public TPeriodo getPeriodoAbierto(Integer id) {
    Session session = this.sessionFactory.openSession();
    TPeriodo periodo = (TPeriodo) session.createQuery("from TPeriodo where estadoPeriodo=true and TEjercicio.idEjercicio=:id ").setParameter("id", id).uniqueResult();
    session.close();  
    return periodo; 
        
    } 
    
    

}
