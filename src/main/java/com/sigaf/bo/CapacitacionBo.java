/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ICapacitacionBo;
import com.sigaf.Idao.ICapacitacionDao;
import com.sigaf.pojo.TCapacitacion;
import java.util.List;

/**
 *
 * @author yonat
 */
public class CapacitacionBo implements ICapacitacionBo {
    
     ICapacitacionDao icapacitacionDao;

    public ICapacitacionDao getIcapacitacionDao() {
        return icapacitacionDao;
    }

    public void setIcapacitacionDao(ICapacitacionDao icapacitacionDao) {
        this.icapacitacionDao = icapacitacionDao;
    }
    

    @Override
    public void create(TCapacitacion capacitacion) {
    
        this.icapacitacionDao.create(capacitacion);
    }

    @Override
    public TCapacitacion getCapacitacion(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TCapacitacion> listCapacitacion() {
    return this.icapacitacionDao.listCapacitacion();
    
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TCapacitacion capacitacion) {
    this.icapacitacionDao.update(capacitacion);
    
    }
    
}
