/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IAgronegocioDao;
import com.sigaf.pojo.TAgronegocio;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author yonat
 */
public class AgronegocioDao implements IAgronegocioDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TAgronegocio Agronegocio) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(Agronegocio);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public TAgronegocio getAgronegocio(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TAgronegocio> listAgronegocio() {

        List<TAgronegocio> listaAgronegocios = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        listaAgronegocios = session.createQuery("from TAgronegocio t inner join fetch t.TComprador order by t.codigo ").list();
        session.getTransaction().commit();
        session.close();
        return listaAgronegocios;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TAgronegocio Agronegocio) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(Agronegocio);    
        session.getTransaction().commit();
        session.close();

    }
    
    @Override
    public Integer getCorrelativo() {

        Session session = this.sessionFactory.openSession();
        TAgronegocio nuevo = (TAgronegocio) session.createQuery("from TAgronegocio where idAgronegocio in (select max(idAgronegocio) from TAgronegocio)").uniqueResult();
        session.close();

        if (nuevo == null) {
            return 1;

        } else {
            return nuevo.getIdAgronegocio() + 1;
        }

    }

    @Override
    public List<TAgronegocio> listAgronegocioCodigo(String codigo) {

        Session session = this.sessionFactory.openSession();
        List<TAgronegocio> listaAgronegocio = session.createQuery("from TAgronegocio p inner join fetch p.TComprador f  where (p.codigo=:id and tipo='Credito' and estado='Pendiente')").setParameter("id", codigo).list();
        session.close();
        return listaAgronegocio;

    }

    @Override
    public List<TAgronegocio> listAgronegocioDui(String codigo) {

        Session session = this.sessionFactory.openSession();
        List<TAgronegocio> listaAgronegocio = session.createQuery("from TAgronegocio p inner join fetch p.TComprador f  where (f.duiComprador=:id and tipo='Credito' and estado='Pendiente')").setParameter("id", codigo).list();
        session.close();
        return listaAgronegocio;

    }

}
