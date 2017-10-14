/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ICuentaBo;
import com.sigaf.Idao.ICuentaDao;
import com.sigaf.pojo.TCuenta;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class CuentaBo implements ICuentaBo {

    private ICuentaDao cuentaDao;

    public ICuentaDao getCuentaDao() {
        return cuentaDao;
    }

    public void setCuentaDao(ICuentaDao cuentaDao) {
        this.cuentaDao = cuentaDao;
    }

    @Override
    public void create(TCuenta cuenta) {

        this.cuentaDao.create(cuenta);

    }

    @Override
    public TCuenta getCuenta(Integer id) {
        return this.cuentaDao.getCuenta(id);
    }

    @Override
    public List<TCuenta> listCuentaSub(Integer id) {
        return this.cuentaDao.listCuentaSub(id);
    }

    @Override
    public List<TCuenta> listCuentaEnt(Integer id) {
        return this.cuentaDao.listCuentaEnt(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TCuenta cuenta) {
        this.cuentaDao.update(cuenta);
    }

    @Override
    public TCuenta getCuentaRep(Integer id, String codigo) {
        return this.cuentaDao.getCuentaRep(id, codigo);
    }

    @Override
    public TCuenta getCuentaRepAct(Integer idEnt, Integer idCue, String codigo) {
        return this.cuentaDao.getCuentaRepAct(idEnt, idCue, codigo);
    }

    @Override
    public List<TCuenta> listCuentaEntAct(Integer id, Boolean estado) {
        return this.cuentaDao.listCuentaEntAct(id, estado);
    }

    @Override
    public List<TCuenta> listCuentaEntActTip(Integer id, Boolean estado) {
        return this.cuentaDao.listCuentaEntActTip(id, estado);
    }

    @Override
    public void updateCode(Integer id, String strOr, String strDe) {
        this.cuentaDao.updateCode(id, strOr, strDe);
    }

    @Override
    public BigDecimal saldoCuenta(Integer idCuenta, Integer idEjercicio, String tipoSaldo) {
        return this.cuentaDao.saldoCuenta(idCuenta, idEjercicio, tipoSaldo);
    }

    @Override
    public List<TCuenta> listCuentaSubDet(Integer idCuenta, Integer idEntidad, Integer idEjercicio, String strCod) {
        return this.cuentaDao.listCuentaSubDet(idCuenta, idEntidad, idEjercicio, strCod);
    }

    @Override
    public BigDecimal saldoCuentaCierre(Integer idCuenta, Integer idEjercicio, String tipoSaldo) {
        return this.cuentaDao.saldoCuentaCierre(idCuenta, idEjercicio, tipoSaldo);
    }

    @Override
    public Integer numeroCuenta(Integer id, Integer strOr) {
            
    return this.cuentaDao.numeroCuenta(id, strOr);
    }

}
