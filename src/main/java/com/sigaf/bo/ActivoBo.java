/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IActivoBo;
import com.sigaf.Idao.IActivoDao;
import com.sigaf.pojo.TActivo;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ActivoBo implements IActivoBo {

    IActivoDao iactivoDao;

    public IActivoDao getIactivoDao() {
        return iactivoDao;
    }

    public void setIactivoDao(IActivoDao iactivoDao) {
        this.iactivoDao = iactivoDao;
    }

    @Override
    public void create(TActivo Activo) {
        this.iactivoDao.create(Activo);
    }

    @Override
    public TActivo getActivo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TActivo> listActivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
    @Override
    public void update(TActivo Activo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TActivo> listActivo(Integer id) {
        return this.iactivoDao.listActivo(id);
    }

    @Override
    public void delete(TActivo activo) {
    
    this.iactivoDao.delete(activo);
    }

}
