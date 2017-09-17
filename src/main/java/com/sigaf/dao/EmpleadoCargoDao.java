/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IEmpleadoCargoDao;
import com.sigaf.pojo.TEmpleadoCargo;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genoves
 */
public class EmpleadoCargoDao implements IEmpleadoCargoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TEmpleadoCargo empleadoCargo) {

        empleadoCargo.setEstadoEmpleadoCargo(Boolean.TRUE);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(empleadoCargo);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TEmpleadoCargo getTEmpleadoCargo(Integer id, Integer idEmpleado) {

        Session session = this.sessionFactory.openSession();
        TEmpleadoCargo empleadoCargo = (TEmpleadoCargo) session.createQuery("from TEmpleadoCargo c where c.TEmpleado.idEmpleado=:idEmpleado and c.TCargo.idCargo =:id and c.estadoEmpleadoCargo=true").setParameter("id", id).setParameter("idEmpleado", idEmpleado).uniqueResult();
        session.close();
        return empleadoCargo;

    }

    @Override
    public List<TEmpleadoCargo> listTEmpleadoCargo(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TEmpleadoCargo> listEmpleadoCargo = session.createQuery("from TEmpleadoCargo c inner join fetch c.TCargo t where c.TEmpleado.idEmpleado=:id").setParameter("id", id).list();
        session.close();
        return listEmpleadoCargo;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEmpleadoCargo empleadoCargo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(empleadoCargo);
        session.getTransaction().commit();
        session.close();
    }

}
