/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ICargoDao;

import com.sigaf.pojo.TCargo;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genoves
 */
public class CargoDao implements ICargoDao {

    
    private List<TCargo> listaCargos;
    

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TCargo cargo) {
        
    Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cargo);
        session.getTransaction().commit();
        session.close();
    
    }

    @Override
    public TCargo getTCargo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TCargo> listTCargo(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TCargo> listaCargos = session.createQuery("from TCargo c order by c.idCargo").list();
        session.close();
        return listaCargos;
    }

    @Override
    public void update(TCargo cargo) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(cargo);
        session.getTransaction().commit();
        session.close();
    
    
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
