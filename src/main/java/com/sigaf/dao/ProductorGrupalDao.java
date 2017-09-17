/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IProductorGrupalDao;
import com.sigaf.pojo.TProductorGrupal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ProductorGrupalDao implements IProductorGrupalDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TProductorGrupal ProductorGrupal) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ProductorGrupal);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TProductorGrupal getProductorGrupal(Integer id) {
        Session session = this.sessionFactory.openSession();
        TProductorGrupal configuracion =  (TProductorGrupal) session.createQuery("from TProductorGrupal where idProductorGrupal =:id").setParameter("id", id).uniqueResult();
        session.close();
        return configuracion;

    }

    @Override
    public List<TProductorGrupal> listProductorGrupal() {

        List<TProductorGrupal> listaProductoresGrupales = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaProductoresGrupales = session.createQuery("from TProductorGrupal where idProductorGrupal!=1").list();
        session.getTransaction().commit();
        session.close();
        return listaProductoresGrupales;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TProductorGrupal ProductorGrupal) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(ProductorGrupal);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public boolean getTProductorGrupalNombre(String nombre) {

        Session session = this.sessionFactory.openSession();

        TProductorGrupal productor = (TProductorGrupal) session.createQuery("from TProductorGrupal where nombreProductorGrupal =:nombre").setParameter("nombre", nombre).uniqueResult();
        session.close();
        if (productor == null) {
            return false;
        } else {

            return true;
        }

    }

}
