/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IProductoAgronegocioDao;
import com.sigaf.pojo.TProductoAgronegocio;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ProductoAgronegocioDao implements IProductoAgronegocioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TProductoAgronegocio ProductoAgronegocio) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ProductoAgronegocio);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TProductoAgronegocio getProductoAgronegocio(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getTProductorAgronegocioNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProductoAgronegocio> listProductoAgronegocio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TProductoAgronegocio ProductoAgronegocio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProductoAgronegocio> listProductoAgronegocio(Integer id) {

        List<TProductoAgronegocio> listaProductosAgro = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaProductosAgro = session.createQuery("from TProductoAgronegocio where TAgronegocio.idAgronegocio=:id").setParameter("id", id).list();
        session.getTransaction().commit();
        session.close();
        return listaProductosAgro;

    }

}
