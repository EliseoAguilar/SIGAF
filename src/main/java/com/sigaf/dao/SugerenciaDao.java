/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ISugerenciaDao;
import com.sigaf.pojo.TSugerencia;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class SugerenciaDao implements ISugerenciaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TSugerencia sugerencia) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(sugerencia);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TSugerencia getSugerencia(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TSugerencia sugerencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TSugerencia> listSugerencia(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TSugerencia> listaSugerencia = session.createQuery("from TSugerencia where TProyecto.idproyecto  =:id").setParameter("id", id).list();
        session.close();
        return listaSugerencia;

    }

}
