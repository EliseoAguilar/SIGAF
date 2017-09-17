/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IConfiguracionAgronegocioBo;
import com.sigaf.Idao.IConfiguracionAgronegocioDao;
import com.sigaf.pojo.TConfiguracionAgronegocio;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ConfiguracionAgronegocioBo implements IConfiguracionAgronegocioBo {
    
    private IConfiguracionAgronegocioDao configuracionAgroDao;
    
    public IConfiguracionAgronegocioDao getConfiguracionAgroDao() {
        return configuracionAgroDao;
    }
    
    public void setConfiguracionAgroDao(IConfiguracionAgronegocioDao configuracionAgroDao) {
        this.configuracionAgroDao = configuracionAgroDao;
    }
    
    @Override
    public void create(TConfiguracionAgronegocio Configuracion) {
        this.configuracionAgroDao.create(Configuracion);
        
    }
    
    @Override
    public TConfiguracionAgronegocio getConfiguracion(Integer id) {
        return this.configuracionAgroDao.getConfiguracion(id);
        
    }
    
    @Override
    public List<TConfiguracionAgronegocio> listConfiguracion() {
        
        return this.configuracionAgroDao.listConfiguracion();
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TConfiguracionAgronegocio Configuracion) {
        this.configuracionAgroDao.update(Configuracion);
        
    }
    
}
