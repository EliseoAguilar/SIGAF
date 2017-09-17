/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IUsuarioDao;
import com.sigaf.pojo.TUsuario;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class UsuarioDao implements IUsuarioDao{

    
      private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TUsuario usuario) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();  
    }

    @Override
    public TUsuario getUsuario(Integer id) {
        
        Session session = this.sessionFactory.openSession();
        TUsuario usuario = (TUsuario) session.createQuery("from TUsuario  u  inner join fetch u.TEmpleado e  inner join fetch u.TEmpleado.TEmpleadoAreas ea where u.idUsuario=:id").setParameter("id", id).uniqueResult();
        session.close();
        return usuario;
    
    }

    @Override
    public List<TUsuario> listUsuario() {
        Session session = this.sessionFactory.openSession();
        List<TUsuario> listaUsuario = session.createQuery("from TUsuario  u  inner join fetch u.TEmpleado e inner join fetch u.TEmpleado.TEmpleadoAreas ea  ").list();
        session.close();
        return listaUsuario;   
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TUsuario usuario) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(usuario);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TUsuario login(String nombre, String clave) {
        
        Session session = this.sessionFactory.openSession();
        TUsuario usuario = (TUsuario) session.createQuery("from TUsuario  as  u  inner join fetch u.TEmpleado e inner join fetch u.TEmpleado.TEmpleadoAreas ea where u.nombreUsuario=:nombre and u.claveUsuario=:clave").setParameter("nombre", nombre).setParameter("clave", clave).uniqueResult();
        session.close();
        return usuario;
    
    }
    
    @Override
    public TUsuario getUsuarioValRep(String nombreUsuario){
        Session session = this.sessionFactory.openSession();
        TUsuario usuario = (TUsuario) session.createQuery("from TUsuario where nombreUsuario=:nombre").setParameter("nombre", nombreUsuario).uniqueResult();
        session.close();
        return usuario;
    }
    
       @Override
    public TUsuario resetPass(String mail){
        Session session = this.sessionFactory.openSession();
        TUsuario usuario = (TUsuario) session.createQuery("from TUsuario  as  u  inner join fetch u.TEmpleado e inner join fetch u.TEmpleado.TEmpleadoAreas ea  where u.TEmpleado.correoEmpleado=:mail").setParameter("mail", mail).uniqueResult();
        session.close();
        return usuario;
    }
    
    
    
}
