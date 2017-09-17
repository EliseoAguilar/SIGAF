/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IBitacoraDao;
import com.sigaf.pojo.TBitacora;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class BitacoraDao implements IBitacoraDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TBitacora bitacora) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(bitacora);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TBitacora getBitacora(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TBitacora> listBitacoraFechas(Integer id, Date fecha1, Date fecha2) {
        Session session = this.sessionFactory.openSession();
        List<TBitacora> listaUsuario = session.createQuery("from TBitacora  as  b  where b.TUsuario.idUsuario=:id and b.fechaBitacora>=:fecha1 and  b.fechaBitacora<=:fecha2 ").setParameter("id", id).setParameter("fecha1", fecha1).setParameter("fecha2", fecha2).list();
        session.close();
        return listaUsuario;
    }

    @Override
    public List<TBitacora> listBitacora(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TBitacora> listaUsuario = session.createQuery("from TBitacora  as  b  where b.TUsuario=:id").setParameter("id", id).list();
        session.close();
        return listaUsuario;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TBitacora bitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
