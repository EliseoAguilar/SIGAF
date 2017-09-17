/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ISugerenciaBo;
import com.sigaf.Idao.ISugerenciaDao;
import com.sigaf.pojo.TSugerencia;
import java.util.List;

/**
 *
 * @author yonat
 */
public class SugerenciaBo implements ISugerenciaBo {
    
    private ISugerenciaDao isugerenciaDao;
    
    public ISugerenciaDao getIsugerenciaDao() {
        return isugerenciaDao;
    }
    
    public void setIsugerenciaDao(ISugerenciaDao isugerenciaDao) {
        this.isugerenciaDao = isugerenciaDao;
    }
    
    @Override
    public void create(TSugerencia sugerencia) {
        this.isugerenciaDao.create(sugerencia);        
    }
    
    @Override
    public TSugerencia getSugerencia(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TSugerencia sugerencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TSugerencia> listSugerencia(Integer id) {
    
    return this.isugerenciaDao.listSugerencia(id);
            
    }
    
}
