/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IActivoFijoBo;
import com.sigaf.Idao.IActivoFijoDao;
import com.sigaf.pojo.TActivoFijo;
import com.sigaf.pojo.TEmpleado;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class ActivoFijoBo implements IActivoFijoBo{

    
   private  IActivoFijoDao activoFijoDao;

    public IActivoFijoDao getActivoFijoDao() {
        return activoFijoDao;
    }

    public void setActivoFijoDao(IActivoFijoDao activoFijoDao) {
        this.activoFijoDao = activoFijoDao;
    }
   
   
   
    @Override
    public void create(TActivoFijo activoFijo) {
    this.activoFijoDao.create(activoFijo);
    }

    @Override
    public TActivoFijo getActivoFijo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TActivoFijo> listActivoFijo(Integer id) {
       return this.activoFijoDao.listActivoFijo(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TActivoFijo activoFijo) {
         this.activoFijoDao.update(activoFijo);
    }
 @Override
 public List<TActivoFijo> listActivoFijoTipo(Integer id){
 return this.activoFijoDao.listActivoFijoTipo(id);
 }
    
    
}
