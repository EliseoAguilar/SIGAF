/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IComentarioDao;
import com.sigaf.pojo.TComentario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ComentarioDao implements IComentarioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TComentario asesoria) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(asesoria);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TComentario getComentario(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TComentario asesoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TComentario> listComentario(Integer id) {

       Session session = this.sessionFactory.openSession();
        List<TComentario> listaComentario = session.createQuery("from TComentario where TProyecto.idproyecto =:id").setParameter("id", id).list();
        session.close();
        return listaComentario;

    }

}
