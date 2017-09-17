/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IPoliticaDao;
import com.sigaf.pojo.TPolitica;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genovés
 */
public class PoliticaDao implements IPoliticaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TPolitica politica) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(politica);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TPolitica getPolitica(Integer id) {

        Session session = this.sessionFactory.openSession();
        TPolitica politica = (TPolitica) session.createQuery("from TPolitica where TTipoCredito.idTipoCredito=:id and estado=true").setParameter("id", id).uniqueResult();
        session.close();
        return politica;

    }

    @Override
    public List<TPolitica> listPolitica(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TPolitica> listaPolitica = session.createQuery("from TPolitica p inner join fetch p.TTipoCredito t where p.TTipoCredito.idTipoCredito=:id ").setParameter("id", id).list();
        session.close();
        return listaPolitica;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Integer id) {

        Session session = this.sessionFactory.openSession();
        Query q = session.createQuery("update TPolitica p set p.estado=false where p.TTipoCredito.idTipoCredito=:id ").setParameter("id", id);
        q.executeUpdate();
        session.close();

    }

    @Override
    public TPolitica getPoliticaHistorica(Integer id) {
        Session session = this.sessionFactory.openSession();
        TPolitica politica = (TPolitica) session.createQuery("from TPolitica where idPolitica=:id").setParameter("id", id).uniqueResult();
        session.close();
        return politica;
    }

}
