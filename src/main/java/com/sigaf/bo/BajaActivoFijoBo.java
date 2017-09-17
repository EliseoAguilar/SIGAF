/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IBajaActivoFijoBo;
import com.sigaf.Idao.IBajaActivoFijoDao;
import com.sigaf.pojo.TBajaActivoFijo;
import java.util.List;

/**
 *
 * @author Eliseo Aguilar
 */
public class BajaActivoFijoBo implements IBajaActivoFijoBo{

    private IBajaActivoFijoDao bajaActivoFijoDao;

    public IBajaActivoFijoDao getBajaActivoFijoDao() {
        return bajaActivoFijoDao;
    }

    public void setBajaActivoFijoDao(IBajaActivoFijoDao bajaActivoFijoDao) {
        this.bajaActivoFijoDao = bajaActivoFijoDao;
    }

    
   
    @Override
    public void create(TBajaActivoFijo bajaActivoFijo) {
      bajaActivoFijoDao.create(bajaActivoFijo);
    }

    @Override
    public TBajaActivoFijo getTBajaActivoFijo(Integer id) {
    return this.bajaActivoFijoDao.getTBajaActivoFijo(id);
    }

    @Override
    public List<TBajaActivoFijo> listBajaActivoFijo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TBajaActivoFijo bajaActivoFijo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
