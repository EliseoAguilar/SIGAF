/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IProductorIndividualDao;
import com.sigaf.pojo.TProductorIndividual;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ProductorIndividualDao implements IProductorIndividualDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TProductorIndividual ProductorIndividual) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ProductorIndividual);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TProductorIndividual getProdcutorGrupal(Integer id) {
        return null;
    }

    @Override
    public List<TProductorIndividual> listProductorIndividual() {

        List<TProductorIndividual> listaProductoresIndividuales = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        listaProductoresIndividuales = session.createQuery("from TProductorIndividual t inner join fetch t.TProductorGrupal").list();

        session.getTransaction().commit();
        session.close();
        return listaProductoresIndividuales;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(TProductorIndividual ProductorIndividual) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(ProductorIndividual);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public boolean getProdcutorIndividual(String dui) {
        Session session = this.sessionFactory.openSession();

        TProductorIndividual productor = (TProductorIndividual) session.createQuery("from TProductorIndividual where duiProductorIndividual =:dui").setParameter("dui", dui).uniqueResult();
        session.close();
        if (productor == null) {
            return false;
        } else {

            return true;
        }
    }

    @Override
    public TProductorIndividual getProdcutorIndividual(Integer id) {

        Session session = this.sessionFactory.openSession();

        TProductorIndividual productor = (TProductorIndividual) session.createQuery("from TProductorIndividual where idProductorIndividual =:id").setParameter("id", id).uniqueResult();
        session.close();

        return productor;

    }

    @Override
    public TProductorIndividual getProdcutorIndividualRepre() {
        Session session = this.sessionFactory.openSession();

        TProductorIndividual productor = (TProductorIndividual) session.createQuery("from TProductorIndividual where representanteGrupal=true").uniqueResult();
        session.close();

        return productor;

    }

    @Override
    public List<TProductorIndividual> listProductorIndividualActivos() {
    List<TProductorIndividual> listaProductoresIndividuales = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        listaProductoresIndividuales = session.createQuery("from TProductorIndividual where estadoProductorIndividual=true").list();

        session.getTransaction().commit();
        session.close();
        return listaProductoresIndividuales;
    
    
    }

}
