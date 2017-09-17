/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IAsesoriaBo;
import com.sigaf.Idao.IAsesoriaDao;
import com.sigaf.pojo.TAsesoria;
import java.util.List;

/**
 *
 * @author yonat
 */
public class AsesoriaBo implements IAsesoriaBo {
    
    IAsesoriaDao iasesoriaDao;
    
    public IAsesoriaDao getIasesoriaDao() {
        return iasesoriaDao;
    }
    
    public void setIasesoriaDao(IAsesoriaDao iasesoriaDao) {
        this.iasesoriaDao = iasesoriaDao;
    }
    
    @Override
    public void create(TAsesoria asesoria) {
        this.iasesoriaDao.create(asesoria);
        
    }
    
    @Override
    public TAsesoria getAsesoria(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TAsesoria> listAsesoria() {
        
        return this.iasesoriaDao.listAsesoria();
        
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TAsesoria asesoria) {
        this.iasesoriaDao.update(asesoria);
    }
    
}
