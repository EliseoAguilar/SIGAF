/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IAreaCultivoBo;
import com.sigaf.Idao.IAreaCultivoDao;
import com.sigaf.pojo.TAreaCultivo;
import java.util.List;

/**
 *
 * @author yonat
 */
public class AreaCultivoBo implements IAreaCultivoBo {
    
    private IAreaCultivoDao areaCultivoDao;
    
    public IAreaCultivoDao getAreaCultivoDao() {
        return areaCultivoDao;
    }
    
    public void setAreaCultivoDao(IAreaCultivoDao areaCultivoDao) {
        this.areaCultivoDao = areaCultivoDao;
    }    
    
    @Override
    public void create(TAreaCultivo AreaCultivo) {
        this.areaCultivoDao.create(AreaCultivo);
    }
    
    @Override
    public TAreaCultivo getAreaCultivo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TAreaCultivo> listAreaCultivo() {
        return this.areaCultivoDao.listAreaCultivo();
        
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TAreaCultivo AreaCultivo) {        
        this.areaCultivoDao.update(AreaCultivo);
    }
    
    @Override
    public boolean getTAreaCultivoNombre(String nombre) {
        
        return this.areaCultivoDao.getTAreaCultivoNombre(nombre);
        
    }

    @Override
    public List<TAreaCultivo> listAreaCultivoActivos() {
      return this.areaCultivoDao.listAreaCultivoActivos();
    
    }
    
}
