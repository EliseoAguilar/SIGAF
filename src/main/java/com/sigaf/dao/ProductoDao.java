/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IProductoDao;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TProducto;
import com.sigaf.pojo.TProductorIndividual;
import com.sigaf.pojo.TTipoCultivo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ProductoDao implements IProductoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TProducto entidad) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entidad);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TProducto getTProducto(Integer id) {

        Session session = this.sessionFactory.openSession();
        TProducto producto = (TProducto) session.createQuery("from TProducto where idProducto =:id").setParameter("id", id).uniqueResult();
        session.close();
        return producto;

    }

    @Override
    public boolean getTProductoCodigo(String codigo, String codigoAux) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProducto> listTProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TProducto entidad) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entidad);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<TProductorIndividual> listTProductorIndividual() {

        List<TProductorIndividual> listaProductoresIndividuales = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaProductoresIndividuales = session.createQuery("from TProductorIndividual").list();
        session.getTransaction().commit();
        session.close();
        return listaProductoresIndividuales;

    }

    @Override
    public List<TAreaCultivo> listTAreaCultivo() {

        List<TAreaCultivo> listaAreas = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaAreas = session.createQuery("from TAreaCultivo").list();
        session.getTransaction().commit();
        session.close();
        return listaAreas;

    }

    @Override
    public List<TTipoCultivo> listTTipoCultivo(Integer id) {

        List<TTipoCultivo> listaCultivos = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaCultivos = session.createQuery("from TTipoCultivo where TAreaCultivo.idAreaCultivo=:id").setParameter("id", id).list();
        session.getTransaction().commit();
        session.close();
        return listaCultivos;

    }

    @Override
    public TTipoCultivo getTCultivo(Integer id) {

        Session session = this.sessionFactory.openSession();
        TTipoCultivo producto = (TTipoCultivo) session.createQuery("from TTipoCultivo where idTipoCultivo =:id").setParameter("id", id).uniqueResult();
        session.close();
        return producto;

    }

    @Override
    public TAreaCultivo getTAreaCultivo(Integer id) {
        Session session = this.sessionFactory.openSession();
        TAreaCultivo areaCultivo = (TAreaCultivo) session.createQuery("from TAreaCultivo where idAreaCultivo =:id").setParameter("id", id).uniqueResult();
        session.close();
        return areaCultivo;

    }

}
