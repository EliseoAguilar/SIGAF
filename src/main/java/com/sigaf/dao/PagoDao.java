/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IPagoDao;
import com.sigaf.pojo.TPago;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class PagoDao implements IPagoDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TPago pago) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(pago);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TPago getTPago(Integer id) {
    
    Session session = this.sessionFactory.openSession();
        TPago pago = (TPago) session.createQuery("from TPago where idpago in (select max(idpago) from TPago where TProyecto.idproyecto=:id) ").setParameter("id", id).uniqueResult();
        session.close();
        return pago;
    
    }

    

    @Override
    public void update(TPago pago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TPago> listTPago(Integer id) {

        Session session = this.sessionFactory.openSession();
        List<TPago> listPago = session.createQuery("from TPago where TProyecto.idproyecto  =:id").setParameter("id", id).list();
        session.close();
        return listPago;

    }
    
     public Integer getCorrelativo() {

        Session session = this.sessionFactory.openSession();
        TPago nuevo = (TPago) session.createQuery("from TPago where referencia in (select max(referencia) from TPago)").uniqueResult();
        session.close();

        if (nuevo == null) {
            return 0;

        } else {
            return (nuevo.getReferencia());
        }

    }

    @Override
    public void delete(TPago pago) {
    
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(pago);
        session.getTransaction().commit();
        session.close();
    }
    
    

}
