/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Idao;

import com.sigaf.pojo.TCuenta;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface ICuentaDao {

    public void create(TCuenta cuenta);

    public TCuenta getCuenta(Integer id);

    public TCuenta getCuentaRep(Integer id, String codigo);

    public TCuenta getCuentaRepAct(Integer idEnt, Integer idCue, String codigo);

    public List<TCuenta> listCuentaEntActTip(Integer id, Boolean estado);

    public List<TCuenta> listCuentaSub(Integer id);

    public List<TCuenta> listCuentaEnt(Integer id);

    public List<TCuenta> listCuentaEntAct(Integer id, Boolean estado);

    public void delete(Integer id);

    public void update(TCuenta cuenta);

    public void updateCode(Integer id, String strOr, String strDe);

    public BigDecimal saldoCuenta(Integer idCuenta, Integer idEjercicio, String tipoSaldo);

    public List<TCuenta> listCuentaSubDet(Integer idCuenta, Integer idEntidad, Integer idEjercicio, String strCod);

    public BigDecimal saldoCuentaCierre(Integer idCuenta, Integer idEjercicio, String tipoSaldo);
    
    public Integer numeroCuenta(Integer id, Integer strOr);
}
