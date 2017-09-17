/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IComercioBo;
import com.sigaf.Idao.IComercioDao;
import com.sigaf.pojo.TComercio;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ComercioBo implements IComercioBo {
    
    private IComercioDao icomercioDao;
    
    public IComercioDao getIcomercioDao() {
        return icomercioDao;
    }
    
    public void setIcomercioDao(IComercioDao icomercioDao) {
        this.icomercioDao = icomercioDao;
    }
    
    @Override
    public void create(TComercio comercio) {
        
        this.icomercioDao.create(comercio);
    }
    
    @Override
    public TComercio getComercio(Integer id) {
        return this.icomercioDao.getComercio(id);
        
    }
    
    @Override
    public List<TComercio> listComercio() {
        return this.icomercioDao.listComercio();
    }
    
    @Override
    public void update(TComercio comercio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(TComercio comercio) {
        this.icomercioDao.delete(comercio);
        
    }
    
}
