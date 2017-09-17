/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IActivoFijoDao;
import com.sigaf.pojo.TActivoFijo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class ActivoFijoDao implements IActivoFijoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TActivoFijo activoFijo) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(activoFijo);
        session.getTransaction().commit();
        session.close(); 
    
    }

    @Override
    public TActivoFijo getActivoFijo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TActivoFijo> listActivoFijo(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TActivoFijo> listaArea = session.createQuery("from TActivoFijo a inner join fetch a.TTipoActivo t"
                + "inner join fetch a.TProveedor p"
                + " inner join fetch a.TEmpleado e"
                + " inner join fetch a.TEmpleado.TEmpleadoAreas ea"
                + " left join fetch  a.TBajaActivoFijos ab"
                + " left join fetch  a.TDepreciacions ad"
                + " inner join fetch  a.TValorActivos av"
                + " where a.TTipoActivo.TEntidad.idEntidad =:id  ").setParameter("id", id).list();
        session.close();
        return listaArea;
    
    }

    
    @Override
    public List<TActivoFijo> listActivoFijoTipo(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TActivoFijo> listaArea = session.createQuery("from TActivoFijo a"
                + " inner join fetch a.TEmpleado e"
                + " inner join fetch a.TEmpleado.TEmpleadoAreas ea"
                + " inner join fetch  a.TValorActivos av"
                + " where a.TTipoActivo.idTipo =:id  ").setParameter("id", id).list();
        session.close();
        return listaArea;
    
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TActivoFijo activoFijo) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(activoFijo);
        session.getTransaction().commit();
        session.close();
    }



}
