/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IClienteDao;
import com.sigaf.pojo.TCliente;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class ClienteDao implements IClienteDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TCliente Agronegocio) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Agronegocio);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TCliente getCliente(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TCliente> listCliente() {

        List<TCliente> listaClientes = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaClientes = session.createQuery("from TCliente").list();
        session.getTransaction().commit();
        session.close();
        return listaClientes;

    }

    @Override
    public void update(TCliente Cliente) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(Cliente);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public List<TCliente> listClienteCodigo() {

        List<TCliente> listaClientes = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaClientes = session.createQuery("from TCliente where codigoCliente!=''").list();
        session.getTransaction().commit();
        session.close();
        return listaClientes;

    }

    @Override
    public void delete(TCliente cliente) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(cliente);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public Boolean getDuiCliente(String dui) {

        Session session = sessionFactory.openSession();
        TCliente cliente = (TCliente) session.createQuery("from TCliente where duiCliente =:dui").setParameter("dui", dui).uniqueResult();
        session.close();
        if (cliente == null) {
            return false;
        } else {

            return true;
        }

    }

    @Override
    public Boolean getNitCliente(String nit) {
       Session session = sessionFactory.openSession();
        TCliente cliente = (TCliente) session.createQuery("from TCliente where nitCliente =:nit").setParameter("nit", nit).uniqueResult();
        session.close();
        if (cliente == null) {
            return false;
        } else {

            return true;
        }  }

}
