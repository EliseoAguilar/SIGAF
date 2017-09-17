/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IEmpleadoAreaDao;
import com.sigaf.pojo.TEmpleadoArea;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class EmpleadoAreaDao implements IEmpleadoAreaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TEmpleadoArea empleadoArea) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(empleadoArea);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TEmpleadoArea getTEmpleadoArea(Integer id, Integer idEmpleado) {
        Session session = this.sessionFactory.openSession();
        TEmpleadoArea empleadoArea = (TEmpleadoArea) session.createQuery("from TEmpleadoArea p where  p.TEmpleado.idEmpleado=:id and p.TArea.nombreArea != 'Socios' ").setParameter("id", idEmpleado).uniqueResult();
        session.close();
        return empleadoArea;

    }

    @Override
    public List<TEmpleadoArea> listTEmpleadoArea(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TEmpleadoArea> listaEntidad = session.createQuery("from TEmpleadoArea where TArea.idArea =:id").setParameter("id", id).list();
        session.close();
        return listaEntidad;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEmpleadoArea empleadoArea) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(empleadoArea);
        session.getTransaction().commit();
        session.clear();
        session.close();
    }

    @Override
    public Boolean correoRepetido(String correo, int idEnt) {
        Session session = sessionFactory.openSession();
        TEmpleadoArea empleadoArea = (TEmpleadoArea) session.createQuery("from TEmpleadoArea e inner join fetch e.TEmpleado f inner join fetch e.TArea a  where f.correoEmpleado=:correo and a.TEntidad.idEntidad=:idEnt").setParameter("correo", correo).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        if (empleadoArea == null) {
            return false;//si no esta manda false
        } else {
            return true;//si ya esta manda true
        }
    }

}
