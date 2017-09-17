/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IEntidadDao;
import com.sigaf.pojo.TEntidad;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genoves
 */
public class EntidadDao implements IEntidadDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TEntidad entidad) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entidad);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TEntidad getTEntidad(Integer id) {
        Session session = this.sessionFactory.openSession();
        TEntidad entidad = (TEntidad) session.createQuery("from TEntidad where idEntidad =:id").setParameter("id", id).uniqueResult();
        session.close();
        return entidad;
    }

    @Override
    public List<TEntidad> listTEndidad() {
        Session session = this.sessionFactory.openSession();
        List<TEntidad> listaEntidad = session.createQuery("from TEntidad where idEntidad !=1 ").list();
        session.close();
        return listaEntidad;
    }

    @Override
    public List<TEntidad> listTEndidadTodos() {
        Session session = this.sessionFactory.openSession();
        List<TEntidad> listaEntidad = session.createQuery("from TEntidad  order by nombreEntidad").list();
        session.close();
        return listaEntidad;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEntidad entidad) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entidad);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean getTEntidadCodigo(String codigo, String codigoAux) {

        Session session = this.sessionFactory.openSession();
        TEntidad entidad = (TEntidad) session.createQuery("from TEntidad where codigoEntidad =:codigo").setParameter("codigo", codigo).uniqueResult();
        session.close();

        if (entidad == null) {
            return false;
        } else {
            return true;
        }

    }
    
    @Override
    public List<TEntidad> listTEndidadCodigo() {
       
     Session session = this.sessionFactory.openSession();
        List<TEntidad> listaEntidad = session.createQuery("from TEntidad where codigoClienteEntidad !='' ").list();
        session.close();
        return listaEntidad;
    
    }


}
