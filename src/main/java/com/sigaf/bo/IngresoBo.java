/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IIngresoBo;
import com.sigaf.Idao.IIngresoDao;
import com.sigaf.pojo.TIngreso;
import java.util.List;

/**
 *
 * @author yonat
 */
public class IngresoBo implements IIngresoBo {
    
    IIngresoDao iingresoDao;
    
    public IIngresoDao getIingresoDao() {
        return iingresoDao;
    }
    
    public void setIingresoDao(IIngresoDao iingresoDao) {
        this.iingresoDao = iingresoDao;
    }
    
    @Override
    public void create(TIngreso Ingreso) {
        this.iingresoDao.create(Ingreso);
    }
    
    @Override
    public TIngreso getIngreso(Integer id) {
        return this.iingresoDao.getIngreso(id);
    }
    
    @Override
    public List<TIngreso> listIngreso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TIngreso Ingreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TIngreso> listIngreso(Integer id) {
        return this.iingresoDao.listIngreso(id);
    }
    
    @Override
    public void delete(TIngreso ingreso) {
        
        this.iingresoDao.delete(ingreso);
        
    }
    
}
