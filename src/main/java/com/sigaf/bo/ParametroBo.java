/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IParametroBo;
import com.sigaf.Idao.IParametroDao;
import com.sigaf.pojo.TParametro;
import com.sigaf.pojo.TParametroseguimiento;
import java.util.List;

/**
 *
 * @author Genovés
 */
public class ParametroBo implements IParametroBo{
    
    private IParametroDao iparametroDao;

    public IParametroDao getIparametroDao() {
        return iparametroDao;
    }

    public void setIparametroDao(IParametroDao iparametroDao) {
        this.iparametroDao = iparametroDao;
    }
    
    

    @Override
    public List<TParametroseguimiento> listParametro() {
        return this.iparametroDao.listParametro();
    }

    @Override
    public void delete(TParametro parametro) {
    this.iparametroDao.delete(parametro);
    }

    @Override
    public void update(TParametro parametro) {
            this.iparametroDao.update(parametro);
    }

    @Override
    public Boolean getParametroRepetido(String nombre) {
      return this.iparametroDao.getParametroRepetido(nombre);
    }

    @Override
    public void create(TParametro paramentro) {
        this.iparametroDao.create(paramentro);
    }

    @Override
    public List<TParametro> listParametro2(Integer id) {
       return this.iparametroDao.listParametro2(id);
    
    }

    @Override
    public TParametro getParametro(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
