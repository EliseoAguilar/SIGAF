/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IPagoAgronegocioDao;
import com.sigaf.pojo.TPagoAgronegocio;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class PagoAgronegocioDao implements IPagoAgronegocioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TPagoAgronegocio PagoAgronegocio) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(PagoAgronegocio);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TPagoAgronegocio getPagoAgronegocio(Integer id) {
        
    Session session = this.sessionFactory.openSession();
        TPagoAgronegocio pago = (TPagoAgronegocio) session.createQuery("from TPagoAgronegocio where TAgronegocio.idAgronegocio =:id").setParameter("id", id).uniqueResult();
        session.close();
        return pago;
       
    }

    @Override
    public boolean getTPagoAgronegocio(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TPagoAgronegocio> listPagoAgronegocio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TPagoAgronegocio PagoAgronegocio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getCorrelativo() {
       
    Session session = this.sessionFactory.openSession();
        TPagoAgronegocio nuevo = (TPagoAgronegocio) session.createQuery("from TPagoAgronegocio where referencia in (select max(referencia) from TPagoAgronegocio)").uniqueResult();
        session.close();

        if (nuevo == null) {
            return 0;

        } else {
            return (nuevo.getReferencia());
        }
    
    }

}
