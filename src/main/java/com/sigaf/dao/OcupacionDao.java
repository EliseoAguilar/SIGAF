/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IOcupacionDao;
import com.sigaf.pojo.TOcupacion;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genoves
 */
public class OcupacionDao implements IOcupacionDao{
    
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TOcupacion ocupacion) {
        
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(ocupacion);
            session.getTransaction().commit();
            session.close();
    }

    @Override
    public TOcupacion getTOcupacion(Integer id, Integer idEmpleado) {
        Session session = this.sessionFactory.openSession();
        TOcupacion ocupacion = (TOcupacion) session.createQuery("from TOcupacion o where o.TEmpleado.idEmpleado=:idEmpleado and o.idOcupacion =:id and o.estadoOcupacion=true").setParameter("id", id).setParameter("idEmpleado", idEmpleado).uniqueResult();
        session.close();
        return ocupacion;
    }

    @Override
    public List<TOcupacion> listTOcupacion(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TOcupacion> listaOcupacion = session.createQuery("from TOcupacion o inner join "
                + "fetch o.TEmpleado e where  e.idEmpleado =:id").setParameter("id", id).list();
        session.close();
        return listaOcupacion;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TOcupacion ocupacion) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(ocupacion);
        session.getTransaction().commit();
        session.close();
    }
    
    
}
