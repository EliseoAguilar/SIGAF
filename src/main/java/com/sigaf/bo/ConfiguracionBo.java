/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IConfiguracionBo;
import com.sigaf.Idao.IConfiguracionDao;
import com.sigaf.pojo.TConfiguracion;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class ConfiguracionBo implements IConfiguracionBo {

    IConfiguracionDao configuracionDao;

    public IConfiguracionDao getConfiguracionDao() {
        return configuracionDao;
    }

    public void setConfiguracionDao(IConfiguracionDao configuracionDao) {
        this.configuracionDao = configuracionDao;
    }

    @Override
    public void create(TConfiguracion configuracion) {
    this.configuracionDao.create(configuracion);
    }

    @Override
    public TConfiguracion getConfiguracion(Integer id) {
    return this.configuracionDao.getConfiguracion(id);
    }

    @Override
    public List<TConfiguracion> listConfiguracion(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TConfiguracion configuracion) {
    this.configuracionDao.update(configuracion);
    }

}
