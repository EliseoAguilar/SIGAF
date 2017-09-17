/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IParametroSeguimientoBo;
import com.sigaf.Idao.IParametroSeguiminetoDao;
import com.sigaf.pojo.TParametro;
import com.sigaf.pojo.TParametroseguimiento;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ParametroSeguimientoBo implements IParametroSeguimientoBo {

    private IParametroSeguiminetoDao iparametroSeguimientoDao;

    public IParametroSeguiminetoDao getIparametroSeguimientoDao() {
        return iparametroSeguimientoDao;
    }

    public void setIparametroSeguimientoDao(IParametroSeguiminetoDao iparametroSeguimientoDao) {
        this.iparametroSeguimientoDao = iparametroSeguimientoDao;
    }

    @Override
    public TParametro getParametroSeguimiento(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TParametroseguimiento> listParametroSeguimiento() {
        return this.iparametroSeguimientoDao.listParametroSeguimiento();

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getParametroRepetido(String nombre) {

        return this.iparametroSeguimientoDao.getParametroRepetido(nombre);
    }

    @Override
    public void create(TParametroseguimiento paramentro) {

        this.iparametroSeguimientoDao.create(paramentro);
    }

    @Override
    public void update(TParametroseguimiento parametro) {

        this.iparametroSeguimientoDao.update(parametro);
    }

    @Override
    public TParametroseguimiento getParametroSeguimientoUnidad(Integer id) {
   return  this.iparametroSeguimientoDao.getParametroSeguimientoUnidad(id);
    
    }

}
