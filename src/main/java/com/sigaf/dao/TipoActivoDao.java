/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ITipoActivoDao;
import com.sigaf.pojo.TTipoActivo;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class TipoActivoDao implements ITipoActivoDao{
    
      private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TTipoActivo tipoActivo) {
        
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(tipoActivo);
            session.getTransaction().commit();
            session.close(); }

    @Override
    public TTipoActivo getActivo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TTipoActivo> listTipoActivo(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TTipoActivo> listaTipoActivo = session.createQuery("from TTipoActivo tp left join fetch tp.TCuentaByIdCuentaVentaTipo "
                + "left join fetch  tp.TCuentaByIdCuentaDepreciacionTipo "
                + "left join fetch  tp.TCuentaByIdCuentaGastoTipo "
                + "left join fetch  tp.TCuentaByIdCuentaActivoTipo "
                + "where tp.TEntidad.idEntidad =:id").setParameter("id", id).list();
        session.close();
        return listaTipoActivo;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TTipoActivo tipoActivo) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(tipoActivo);
        session.getTransaction().commit();
        session.close();
    
    }

    @Override
    public TTipoActivo getActivoRepAct(Integer idEnt, Integer idTip, String codigo) {
        Session session = this.sessionFactory.openSession();
        TTipoActivo tipo = ( TTipoActivo) session.createQuery("from TTipoActivo where codigoTipo=:codigo and  idTipo!=:idTip and TEntidad.idEntidad  =:idEnt ").setParameter("codigo",codigo).setParameter("idTip",idTip).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return  tipo; 
    }

    @Override
    public TTipoActivo getActivoRep(Integer idEnt, String codigo) {
        Session session = this.sessionFactory.openSession();
        TTipoActivo tipo = ( TTipoActivo) session.createQuery("from TTipoActivo where codigoTipo=:codigo and TEntidad.idEntidad  =:idEnt ").setParameter("codigo",codigo).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return  tipo; 
    }
    
}
