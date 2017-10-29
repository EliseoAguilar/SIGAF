/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.IPartidaDao;
import com.sigaf.pojo.TPartida;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class PartidaDao implements IPartidaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TPartida partida) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(partida);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public TPartida getPartida(Integer id) {
        Session session = this.sessionFactory.openSession();
        TPartida listPartida = (TPartida) session.createQuery("from TPartida p where  p.idPartida=:id").setParameter("id", id).uniqueResult();
        session.close();
        return listPartida;
    }

    @Override
    public List<TPartida> listPartida(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TPartida> listPartida = session.createQuery("select distinct p from TPartida p"
                + " left join fetch  p.TBajaActivoFijos ab"
                + " left join fetch  p.TDepreciacions ad"
                + " left join fetch p.TValorActivos av"
                + " where  p.TPeriodo.idPeriodo=:id order by p.numeroPartida DESC").setParameter("id", id).list();
        session.close();
        return listPartida;

    }

    @Override
    public void delete(TPartida partida, Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query q = session.createSQLQuery("update  t_partida  set numero_partida= numero_partida -1  where numero_partida > :num  and id_periodo_partida in (select id_periodo from t_periodo where id_ejercicio =:id)").setParameter("num", partida.getNumeroPartida()).setParameter("id", id);
        q.executeUpdate();
        session.delete(partida);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(TPartida partida) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(partida);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Integer numeroPartida(Integer id) {
        Session session = this.sessionFactory.openSession();
        Integer numero = (Integer) session.createSQLQuery("select COALESCE( max(numero_partida), 0) from t_partida inner join t_periodo on t_partida.id_periodo_partida=t_periodo.id_periodo  where t_periodo.id_ejercicio =:id ").setParameter("id", id).uniqueResult();
        session.close();
        return numero + 1;

    }

}
