/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IParametroDao;
import com.sigaf.pojo.TParametro;
import com.sigaf.pojo.TParametroseguimiento;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genovés
 */
public class ParametroDao implements IParametroDao {

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
    public void create(TParametro paramentro) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(paramentro);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TParametroseguimiento getParametro(Integer id) {
        Session session = this.sessionFactory.openSession();
        TParametroseguimiento parametro = (TParametroseguimiento) session.createQuery("from TParametroseguimiento p where p.idparametro = :id ").setParameter("id", id).uniqueResult();
        session.close();
        return parametro;
    }

    @Override
    public List<TParametroseguimiento> listParametro() {
        Session session = this.sessionFactory.openSession();
        List<TParametroseguimiento> listParametro = session.createQuery("from TParametroseguimiento where estado=1").list();
        session.close();
        return listParametro;
    }

    @Override
    public void delete(TParametro parametro) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(parametro);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void update(TParametro parametro) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(parametro);
        session.getTransaction().commit();
        session.close();
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
    public List<TParametro> listParametro2(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TParametro> listParametro = session.createQuery("from TParametro t inner join fetch t.TParametroseguimiento where t.TSeguimiento.idseguimiento=:id ").setParameter("id", id).list();
        session.close();
        return listParametro;

    }

}
