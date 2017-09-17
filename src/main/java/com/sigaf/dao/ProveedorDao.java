/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IProveedorDao;
import com.sigaf.pojo.TProveedor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class ProveedorDao  implements IProveedorDao{
    
     private SessionFactory sessionFactory;
    

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(TProveedor proveedor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(proveedor);
        session.getTransaction().commit();
        session.close();   }

    @Override
    public TProveedor getProveedor(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProveedor> listProveedor(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TProveedor> listProveedor = session.createQuery("from TProveedor where TEntidad.idEntidad  =:id").setParameter("id", id).list();
        session.close();
        return listProveedor; 
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TProveedor proveedor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(proveedor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TProveedor getProveedorRepNit(Integer idEnt, String nit) {
        Session session = this.sessionFactory.openSession();
        TProveedor proveedor = (TProveedor) session.createQuery("from TProveedor where nitProveedor=:nit  and TEntidad.idEntidad  =:idEnt ").setParameter("nit",nit).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return proveedor;
    }

    @Override
    public TProveedor getProveedorRepActNit(Integer idEnt, Integer idProv, String nit) {
         Session session = this.sessionFactory.openSession();
        TProveedor proveedor = (TProveedor) session.createQuery("from TProveedor where nitProveedor=:nit and idProveedor!=:idProv and TEntidad.idEntidad  =:idEnt ").setParameter("nit",nit).setParameter("idProv",idProv).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return proveedor;
    }

    @Override
    public TProveedor getProveedorRepNrc(Integer idEnt, String nrc) {
        Session session = this.sessionFactory.openSession();
        TProveedor proveedor = (TProveedor) session.createQuery("from TProveedor where nrcProveedor=:nrc  and TEntidad.idEntidad  =:idEnt ").setParameter("nrc",nrc).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return proveedor;    
    }

    @Override
    public TProveedor getProveedorRepActNrc(Integer idEnt, Integer idProv, String nrc) {
        Session session = this.sessionFactory.openSession();
        TProveedor proveedor = (TProveedor) session.createQuery("from TProveedor where nrcProveedor=:nrc and idProveedor!=:idProv and TEntidad.idEntidad  =:idEnt ").setParameter("nrc",nrc).setParameter("idProv",idProv).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return proveedor;    
    }
    
    
       @Override
    public TProveedor getProveedorRepCorreo(Integer idEnt, String correo) {
        Session session = this.sessionFactory.openSession();
        TProveedor proveedor = (TProveedor) session.createQuery("from TProveedor where correoProveedor=:correo  and TEntidad.idEntidad  =:idEnt ").setParameter("correo",correo).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return proveedor;
    }

    @Override
    public TProveedor getProveedorRepActCorreo(Integer idEnt, Integer idProv, String correo) {
         Session session = this.sessionFactory.openSession();
        TProveedor proveedor = (TProveedor) session.createQuery("from TProveedor where correoProveedor=:correo and idProveedor!=:idProv and TEntidad.idEntidad  =:idEnt ").setParameter("correo",correo).setParameter("idProv",idProv).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return proveedor;
    }
    
    
}
