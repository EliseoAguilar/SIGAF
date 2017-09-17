/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IParametroSeguiminetoDao;
import com.sigaf.pojo.TParametro;
import com.sigaf.pojo.TParametroseguimiento;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ParametroSeguimientoDao implements IParametroSeguiminetoDao {

    private SessionFactory sessionFactory;

    private Boolean bandera;

    public Boolean getBandera() {
        return bandera;
    }

    public void setBandera(Boolean bandera) {
        this.bandera = bandera;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public TParametro getParametroSeguimiento(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TParametroseguimiento> listParametroSeguimiento() {
        Session session = this.sessionFactory.openSession();
        List<TParametroseguimiento> listParametro = session.createQuery("from TParametroseguimiento").list();
        session.close();
        return listParametro;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getParametroRepetido(String nombre) {

        Session session = this.sessionFactory.openSession();
        TParametroseguimiento parametro = (TParametroseguimiento) session.createQuery("from TParametroseguimiento p where p.nombre = :nombre ").setParameter("nombre", nombre).uniqueResult();
        session.close();
        this.bandera = parametro != null;

        return bandera;

    }

    @Override
    public void create(TParametroseguimiento paramentro) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(paramentro);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(TParametroseguimiento parametro) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(parametro);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TParametroseguimiento getParametroSeguimientoUnidad(Integer id) {

        Session session = this.sessionFactory.openSession();
        TParametroseguimiento parametro = (TParametroseguimiento) session.createQuery("from TParametroseguimiento p where p.idparametro = :id ").setParameter("id", id).uniqueResult();
        session.close();
        return parametro;

    }

}
