/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IProductoProductorDao;
import com.sigaf.pojo.TProductoProductor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ProductoProductorDao implements IProductoProductorDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TProductoProductor productoProductor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(productoProductor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TProductoProductor getTProductorProductor(Integer id) {

        Session session = this.sessionFactory.openSession();
        TProductoProductor productoProductor = (TProductoProductor) session.createQuery("from TProductoProductor where idProductoProductor =:id").setParameter("id", id).uniqueResult();
        session.close();
        return productoProductor;

    }

    @Override
    public boolean getTProductoProductorCodigo(String codigo, String codigoAux) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TProductoProductor productoProductor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProductoProductor> listTProductoProductor(Integer id) {
        
        Session session = this.sessionFactory.openSession();
        List<TProductoProductor> listaProductoProductor = session.createQuery("from TProductoProductor  where TProductorIndividual.idProductorIndividual=:id ").setParameter("id", id).list();
        session.close();
        return listaProductoProductor;      
        

    }

    @Override
    public List<TProductoProductor> listTProductoProductorCod(Integer idproducto) {
    
    Session session = this.sessionFactory.openSession();
        List<TProductoProductor> listaProductoProductor = session.createQuery("from TProductoProductor  where TProducto.idproducto=:id").setParameter("id", idproducto).list();
        session.close();
        return listaProductoProductor; 
    
    }

}
