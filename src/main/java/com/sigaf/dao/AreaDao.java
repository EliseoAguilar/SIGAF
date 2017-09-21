/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IAreaDao;
import com.sigaf.pojo.TArea;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Genoves
 */
public class AreaDao implements IAreaDao {

    private SessionFactory sessionFactory;  

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TArea area) {

        area.setEstadoArea(true);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(area);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TArea getTArea(Integer id) {
    
     Session session = this.sessionFactory.openSession();
        TArea area = (TArea) session.createQuery("from TArea where idArea=:id").setParameter("id", id).uniqueResult();
        session.close();
        return area;
    
    }

    @Override
    public List<TArea> listTArea(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TArea> listaArea = session.createQuery("select distinct ar from TArea ar left join fetch ar.TEmpleadoAreas arem  where ar.TEntidad.idEntidad =:id and ar.nombreArea != 'Socios' order by ar.codigoArea ").setParameter("id", id).list();
        session.close();
        return listaArea;

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TArea area) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(area);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TArea getTAreaRepAct(Integer idEnt, Integer idAre, String codigo) {
        Session session = this.sessionFactory.openSession();
        TArea area = (TArea) session.createQuery("from TArea  where codigoArea=:codigo and idArea!=:idAre and TEntidad.idEntidad  =:idEnt").setParameter("codigo", codigo).setParameter("idAre", idAre).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return area;
    }

    @Override
    public TArea getTAreaRep(Integer idEnt, String codigo) {
        Session session = this.sessionFactory.openSession();
        TArea area = (TArea) session.createQuery("from TArea where codigoArea=:codigo and TEntidad.idEntidad  =:idEnt ").setParameter("codigo", codigo).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return area;
    }

    @Override
    
    /* Yonatan le tiene que Cambiar el nombre*/
    public List<TArea> listArea2(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TArea> listaArea = session.createQuery("select distinct ar from TArea ar left join fetch ar.TEmpleadoAreas arem where ar.TEntidad.idEntidad =:id and ar.nombreArea = 'Socios' and ar.estadoArea=true").setParameter("id", id).list();
        session.close();
        return listaArea;

    }

    @Override
    public List<TArea> listAreaActivas(Integer id) {
    Session session = this.sessionFactory.openSession();
        List<TArea> listaArea = session.createQuery("select distinct ar from TArea ar left join fetch ar.TEmpleadoAreas arem  where ar.TEntidad.idEntidad =:id and ar.nombreArea != 'Socios' and ar.estadoArea=true order by ar.codigoArea ").setParameter("id", id).list();
        session.close();
        return listaArea; }

}
