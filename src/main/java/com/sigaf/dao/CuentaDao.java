/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.dao;

import com.sigaf.Idao.ICuentaDao;
import com.sigaf.pojo.TCuenta;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Eliseo
 */
public class CuentaDao implements ICuentaDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Metodo para guardar en la base de datos una cuenta nueva.
     *
     * @param cuenta
     */
    @Override
    public void create(TCuenta cuenta) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cuenta);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metodo para consultar una cuenta especifica por su ID.
     *
     * @param id
     * @return TCuenta
     */
    @Override
    public TCuenta getCuenta(Integer id) {

        Session session = this.sessionFactory.openSession();
        TCuenta cuenta = (TCuenta) session.createQuery("from TCuenta where idCuenta  =:id").setParameter("id", id).uniqueResult();
        session.close();
        return cuenta;
    }

    /**
     * metodo para verificar si una cuenta dispone de subcuentas, esto es para
     * validadar.
     *
     * @param id
     * @return List
     */
    @Override
    public List<TCuenta> listCuentaSub(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TCuenta> listCuenta = session.createQuery("from TCuenta as a  where  a.TCuenta.idCuenta=:id order by codigoCuenta").setParameter("id", id).list();
        session.close();
        return listCuenta;
    }

    /**
     * Metodo para listar todas la cuentas de una entidad especifica.
     *
     * @param id
     * @return List
     */
    @Override
    public List<TCuenta> listCuentaEnt(Integer id) {
        Session session = this.sessionFactory.openSession();
        List<TCuenta> listCuenta = session.createQuery("select distinct c from TCuenta c left join fetch c.TCuentas  where c.TEntidad.idEntidad  =:id order by c.codigoCuenta").setParameter("id", id).list();
        session.close();
        return listCuenta;
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TCuenta cuenta) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(cuenta);
        session.getTransaction().commit();
        session.close();

    }

    /**
     * Metodo para buscar si el codigo de una nueva cuenta ya existe en la base
     * de datos, para la misma entidad.
     *
     * @param id
     * @param codigo
     * @return TCuenta
     */
    @Override
    public TCuenta getCuentaRep(Integer id, String codigo) {
        Session session = this.sessionFactory.openSession();
        TCuenta cuenta = (TCuenta) session.createQuery("from TCuenta where codigoCuenta=:codigo and TEntidad.idEntidad  =:id ").setParameter("codigo", codigo).setParameter("id", id).uniqueResult();
        session.close();
        return cuenta;
    }

    /**
     * Metodo para buscar si el codigo de una cuenta a actualizar ya existe en
     * la base de datos excluyedo dicha cuenta, para la misma entidad.
     *
     * @param idEnt
     * @param idCue
     * @param codigo
     * @return TCuenta
     */
    @Override
    public TCuenta getCuentaRepAct(Integer idEnt, Integer idCue, String codigo) {
        Session session = this.sessionFactory.openSession();
        TCuenta cuenta = (TCuenta) session.createQuery("from TCuenta where codigoCuenta=:codigo and idCuenta!=:idCue and TEntidad.idEntidad  =:idEnt ").setParameter("codigo", codigo).setParameter("idCue", idCue).setParameter("idEnt", idEnt).uniqueResult();
        session.close();
        return cuenta;
    }

    /*
    *Retorna todas las cuentas segun el estado (activo, inactivo) 
    *y excluye las de detalle(cuentas que an sido usadas en el libro diario o en tipos de activos)
     */
    @Override
    public List<TCuenta> listCuentaEntAct(Integer id, Boolean estado) {
        Session session = this.sessionFactory.openSession();
        List<TCuenta> listCuenta = session.createQuery("from TCuenta c  where  c.estadoCuenta=:estado and   c.TEntidad.idEntidad =:id and c.idCuenta "
                + "not in (select c.idCuenta from TDetallePartida d  inner join  d.TCuenta  c where c.TEntidad.idEntidad =:id )"
                + "and c.idCuenta not in ( select c.idCuenta from TTipoActivo t inner join  t.TCuentaByIdCuentaVentaTipo c where c.TEntidad.idEntidad =:id ) "
                + "and c.idCuenta not in ( select c.idCuenta from TTipoActivo t inner join  t.TCuentaByIdCuentaDepreciacionTipo c where c.TEntidad.idEntidad =:id )"
                + "and c.idCuenta not in ( select c.idCuenta from TTipoActivo t inner join  t.TCuentaByIdCuentaGastoTipo c where c.TEntidad.idEntidad =:id )"
                + "and c.idCuenta not in ( select c.idCuenta from TTipoActivo t inner join  t.TCuentaByIdCuentaActivoTipo c where c.TEntidad.idEntidad =:id ) order by c.codigoCuenta"
        ).setParameter("estado", estado).setParameter("id", id).list();
        session.close();
        return listCuenta;
    }

    /**
     * Retorna todas la cuentas de detalles
     *
     * @param id
     * @param estado
     * @return list
     */
    @Override
    public List<TCuenta> listCuentaEntActTip(Integer id, Boolean estado) {
        Session session = this.sessionFactory.openSession();
        List<TCuenta> listCuenta = session.createQuery("from TCuenta c inner join fetch c.TCuenta where c.estadoCuenta=:estado and c.TEntidad.idEntidad  =:id and c.idCuenta not in( select TCuenta.idCuenta from TCuenta where TEntidad.idEntidad  =:id and TCuenta.idCuenta!=0 ) order by c.codigoCuenta").setParameter("estado", estado).setParameter("id", id).list();
        session.close();
        return listCuenta;
    }

    /**
     * Actuliza el codigo de todas la cuentas hijas del segmento correspondiente
     * al padre que se actulizo previamente.
     *
     * @param id
     * @param strOr
     * @param strDe
     */
    @Override
    public void updateCode(Integer id, String strOr, String strDe) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createSQLQuery("update t_cuenta set codigo_cuenta=regexp_replace(codigo_cuenta, :strOr, :strDe) where  id_entidad_cuenta =:id and codigo_cuenta like '" + strOr + "%'").setParameter("strOr", strOr).setParameter("strDe", strDe).setParameter("id", id).executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    /*
       * Metodo que retorna el salfo acumulado en las  cuentas segun el parametro 
       * tipo de saldo 
     */
    @Override
    public BigDecimal saldoCuenta(Integer idCuenta, Integer idEjercicio, String tipoSaldo) {

        Session session = this.sessionFactory.openSession();
        BigDecimal saldo = (BigDecimal) session.createSQLQuery("WITH RECURSIVE libro_mayor(id_cuenta, id_subcuenta_cuenta ,codigo_cuenta,nombre_cuenta ,naturaleza_cuenta, orden_cuenta , total_debe  ) as"
                + "     (select c.id_cuenta, c.id_subcuenta_cuenta, c.codigo_cuenta,c.nombre_cuenta , c.naturaleza_cuenta,"
                + "     CAST('/' || CAST(id_cuenta AS VARCHAR(15)) || '/' AS varchar(900)) AS  orden_cuenta, COALESCE( "
                + "	(select sum(saldo_detalle_partida) from t_detalle_partida dt  inner join t_partida pt on dt.id_partida_detalle_partida= pt.id_partida"
                + "	inner join t_periodo pr on pr.id_periodo=pt.id_periodo_partida where tipo_saldo_detalle_partida=:tipoSaldo and id_cuenta_detalle_partida=c.id_cuenta "
                + "	and id_ejercicio=:idEjercicio and pt.estado_partida=true  group by id_cuenta_detalle_partida),0) as total_debe from t_cuenta c"
                + "	union all"
                + "	select  c.id_cuenta,c.id_subcuenta_cuenta, c.codigo_cuenta,c.nombre_cuenta , c.naturaleza_cuenta,"
                + "	CAST( '/' ||  CAST(c.id_cuenta AS VARCHAR(15))  || l.orden_cuenta  AS varchar(900) ) AS orden_cuenta, l.total_debe"
                + "	from t_cuenta c inner join libro_mayor l  on  c.id_cuenta=l.id_subcuenta_cuenta)"
                + "	select   sum(total_debe) from libro_mayor lb where  lb.id_cuenta =:idCuenta group by id_cuenta").setParameter("idCuenta", idCuenta).setParameter("idEjercicio", idEjercicio).setParameter("tipoSaldo", tipoSaldo).uniqueResult();
        session.close();

        return saldo;

    }
    
    
    
      
    
    
    
    
    /*
    * Retorna la lista de cuantas a nivel de detalle de la cuenta selecciona
    * para el balance, esto es para el registro de inicio de operaciones
    */
    @Override
     public List<TCuenta> listCuentaSubDet(Integer idCuenta,Integer idEntidad, Integer idEjercicio, String strCod) {
        Session session = this.sessionFactory.openSession();
        List<TCuenta> listCuenta =  session.createSQLQuery(" select * from t_cuenta where  codigo_cuenta like '" + strCod + "%' and id_entidad_cuenta="+ idEntidad +" and id_cuenta!="+ idCuenta +
                                                            "and  id_cuenta in (select id_cuenta_detalle_partida from  t_detalle_partida dt " +
                                                            "inner join t_partida pt on dt.id_partida_detalle_partida = pt.id_partida " +
                                                            "inner join t_periodo pr on pr.id_periodo=pt.id_periodo_partida where " +
                                                            "id_ejercicio="+ idEjercicio +"  group by id_cuenta_detalle_partida )"
                                                            ).addEntity(TCuenta.class).list();
        session.close();
        return listCuenta;
     }
     
     
     /*
       * Metodo que retorna el salfo acumulado en las  cuentas segun el parametro 
       * tipo de saldo 
     */
    @Override
    public BigDecimal saldoCuentaCierre(Integer idCuenta, Integer idEjercicio, String tipoSaldo) {

        Session session = this.sessionFactory.openSession();
        BigDecimal saldo = (BigDecimal) session.createSQLQuery("WITH RECURSIVE libro_mayor(id_cuenta, id_subcuenta_cuenta ,codigo_cuenta,nombre_cuenta ,naturaleza_cuenta, orden_cuenta , total_debe  ) as"
                + "     (select c.id_cuenta, c.id_subcuenta_cuenta, c.codigo_cuenta,c.nombre_cuenta , c.naturaleza_cuenta,"
                + "     CAST('/' || CAST(id_cuenta AS VARCHAR(15)) || '/' AS varchar(900)) AS  orden_cuenta, COALESCE( "
                + "	(select sum(saldo_detalle_partida) from t_detalle_partida dt  inner join t_partida pt on dt.id_partida_detalle_partida= pt.id_partida"
                + "	inner join t_periodo pr on pr.id_periodo=pt.id_periodo_partida where tipo_saldo_detalle_partida=:tipoSaldo and id_cuenta_detalle_partida=c.id_cuenta "
                + "	and id_ejercicio=:idEjercicio   group by id_cuenta_detalle_partida),0) as total_debe from t_cuenta c"
                + "	union all"
                + "	select  c.id_cuenta,c.id_subcuenta_cuenta, c.codigo_cuenta,c.nombre_cuenta , c.naturaleza_cuenta,"
                + "	CAST( '/' ||  CAST(c.id_cuenta AS VARCHAR(15))  || l.orden_cuenta  AS varchar(900) ) AS orden_cuenta, l.total_debe"
                + "	from t_cuenta c inner join libro_mayor l  on  c.id_cuenta=l.id_subcuenta_cuenta)"
                + "	select   sum(total_debe) from libro_mayor lb where  lb.id_cuenta =:idCuenta group by id_cuenta").setParameter("idCuenta", idCuenta).setParameter("idEjercicio", idEjercicio).setParameter("tipoSaldo", tipoSaldo).uniqueResult();
        session.close();

        return saldo;

    }
}
