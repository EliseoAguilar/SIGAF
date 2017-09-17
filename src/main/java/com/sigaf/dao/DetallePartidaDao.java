/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IDetallePartidaDao;
import com.sigaf.pojo.TDetallePartida;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class DetallePartidaDao implements IDetallePartidaDao{

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void create(TDetallePartida detallePartida) {
     Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(detallePartida);
        session.getTransaction().commit();
        session.close();    
    }

    @Override
    public TDetallePartida getDetallePartida(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TDetallePartida> listDetallePartida(Integer id) {
        
        List<TDetallePartida > listaDetallePartida = null;
        Session session = sessionFactory.openSession();
        listaDetallePartida = session.createQuery("from TDetallePartida where TPartida.idPartida =:id order by tipoSaldoDetallePartida  ").setParameter("id", id).list();
        session.close();
        return listaDetallePartida;
    }

    @Override
    public void delete(Integer id) {
      Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        Query q=session.createSQLQuery("delete from t_detalle_partida where  id_partida_detalle_partida=:id").setParameter("id", id);
        q.executeUpdate();
       
        session.getTransaction().commit();
        session.close();    
    }

    @Override
    public void update(TDetallePartida detallePartida) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(detallePartida);
        session.getTransaction().commit();
        session.close();    
    }
    
}
