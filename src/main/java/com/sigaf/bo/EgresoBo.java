/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IEgresoBo;
import com.sigaf.Idao.IEgresoDao;
import com.sigaf.pojo.TEgreso;
import java.util.List;

/**
 *
 * @author yonat
 */
public class EgresoBo implements IEgresoBo {
    
    IEgresoDao iegresoDao;
    
    public IEgresoDao getIegresoDao() {
        return iegresoDao;
    }
    
    public void setIegresoDao(IEgresoDao iegresoDao) {
        this.iegresoDao = iegresoDao;
    }
    
    @Override
    public void create(TEgreso Egreso) {
        this.iegresoDao.create(Egreso);
        
    }
    
    @Override
    public TEgreso getEgreso(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TEgreso> listEgreso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TEgreso Egreso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TEgreso> listEgreso(Integer id) {
        return this.iegresoDao.listEgreso(id);
    }
    
    @Override
    public void delete(TEgreso egreso) {
        this.iegresoDao.delete(egreso);
        
    }
    
}
