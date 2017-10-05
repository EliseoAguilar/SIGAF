/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IEntidadProyectoDao;
import com.sigaf.pojo.TEntidadProyecto;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class EntidadProyectoDao implements IEntidadProyectoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TEntidadProyecto entidadProyecto) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entidadProyecto);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TEntidadProyecto getTEntidadProyecto(Integer id) {
       
        Session session = this.sessionFactory.openSession();
        TEntidadProyecto garantia = (TEntidadProyecto) session.createQuery("from TEntidadProyecto where TProyecto.idproyecto =:id").setParameter("id", id).uniqueResult();
        session.close();
        return garantia;


    
    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectos() {
        Session session = this.sessionFactory.openSession();
        List<TEntidadProyecto> listaEntidadProyecto = session.createQuery("from TEntidadProyecto t inner join fetch t.TProyecto  inner join fetch t.TEntidad").list();
        session.close();
        return listaEntidadProyecto;

    }

    @Override
    public void update(TEntidadProyecto entidad) {
    
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.update(entidad);
        session.getTransaction().commit();
        session.clear();
    
    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectos(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TEntidadProyecto> listaEntidadProyecto = session.createQuery("from TEntidadProyecto p inner join fetch p.TProyecto f inner join fetch p.TEntidad c where (f.TTipoCredito.idTipoCredito=3 OR f.TTipoCredito.idTipoCredito=2) AND f.estado=1").list();
        session.close();
        return listaEntidadProyecto;

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosResolucion(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TEntidadProyecto> listaEntidadProyecto = session.createQuery("from TEntidadProyecto p inner join fetch p.TProyecto f inner join fetch p.TEntidad c where (f.TTipoCredito.idTipoCredito=3 OR f.TTipoCredito.idTipoCredito=2) AND f.estado=2").list();
        session.close();
        return listaEntidadProyecto;

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosAprovados(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TEntidadProyecto> listaEntidadProyecto = session.createQuery("from TEntidadProyecto p inner join fetch p.TProyecto f inner join fetch p.TEntidad c where (f.TTipoCredito.idTipoCredito=3 OR f.TTipoCredito.idTipoCredito=2) AND (f.estado=3 or f.estado=5 or f.estado=6 or f.estado=7)").list();
        session.close();
        return listaEntidadProyecto;

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosAprovadosClinte(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TEntidadProyecto> listaEntidadProyecto = session.createQuery("from TEntidadProyecto p inner join fetch p.TProyecto f inner join fetch p.TEntidad c where (f.TTipoCredito.idTipoCredito=3 OR f.TTipoCredito.idTipoCredito=2) AND ((f.estado=3 or f.estado=5 or f.estado=6) AND identidad=:id)").setParameter("id", id).list();
        session.close();
        return listaEntidadProyecto;

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosNoAprobados() {

        Session session = this.sessionFactory.openSession();
        List<TEntidadProyecto> listaEntidadProyecto = session.createQuery("from TEntidadProyecto p inner join fetch p.TProyecto f inner join fetch p.TEntidad c where (f.TTipoCredito.idTipoCredito=3 OR f.TTipoCredito.idTipoCredito=2) AND (f.estado=4)").list();
        session.close();
        return listaEntidadProyecto;

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosTodas(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TEntidadProyecto> listaEntidadProyecto = session.createQuery("from TEntidadProyecto p inner join fetch p.TProyecto f inner join fetch p.TEntidad c where (f.TTipoCredito.idTipoCredito=3 OR f.TTipoCredito.idTipoCredito=2) AND (f.estado=3 or f.estado=4 or f.estado=5 or f.estado=6)").list();
        session.close();
        return listaEntidadProyecto;

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosEjecutandose(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TEntidadProyecto> listaEntidadProyecto = session.createQuery("from TEntidadProyecto p inner join fetch p.TProyecto f inner join fetch p.TEntidad c where (f.TTipoCredito.idTipoCredito=3 OR f.TTipoCredito.idTipoCredito=2) AND (f.estado=5 or f.estado=6 or f.estado=3 or f.estado=7)").list();
        session.close();
        return listaEntidadProyecto;

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosAprovadosEjecutandose(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TEntidadProyecto> listaEntidadProyecto = session.createQuery("from TEntidadProyecto p inner join fetch p.TProyecto f inner join fetch p.TEntidad c where (f.TTipoCredito.idTipoCredito=3 OR f.TTipoCredito.idTipoCredito=2) AND (f.estado=5)").list();
        session.close();
        return listaEntidadProyecto;

    }

    @Override
    public void delete(TEntidadProyecto entidadProyecto) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(entidadProyecto);
        session.getTransaction().commit();
        session.close();
        

    }

}
