/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IEstructuraDao;
import com.sigaf.pojo.TEstructura;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class EstructuraDao implements IEstructuraDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TEstructura estructura) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(estructura);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TEstructura getEstructura(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TEstructura> listEstructura(Integer idEjercicio, Integer idTipo) {

        Session session = this.sessionFactory.openSession();
        List<TEstructura> listaArea = session.createQuery("from TEstructura as e  inner join  fetch  e.TCuenta as cu where  e.TEjercicio.idEjercicio=:idEjercicio and e.tipoReporte=:idTipo ").setParameter("idEjercicio", idEjercicio).setParameter("idTipo", idTipo).list();
        session.close();
        return listaArea;

    }

    @Override
    public void delete(Integer id, Integer tipoRep) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Query q=session.createSQLQuery("delete from t_estructura where  id_ejercicio_estructura=:id and tipo_reporte=:tipoRep").setParameter("id", id).setParameter("tipoRep", tipoRep) ;
        q.executeUpdate();
       
        session.getTransaction().commit();
        session.close();  
    }

    @Override
    public void update(TEstructura estructura) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(estructura);
        session.getTransaction().commit();
        session.close();
    }

}
