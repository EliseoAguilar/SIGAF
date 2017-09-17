/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ITipoActivoBo;
import com.sigaf.Idao.ITipoActivoDao;
import com.sigaf.pojo.TTipoActivo;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class TipoActivoBo implements ITipoActivoBo{
    
    private ITipoActivoDao tipoActivoDao;

    public ITipoActivoDao getTipoActivoDao() {
        return tipoActivoDao;
    }

    public void setTipoActivoDao(ITipoActivoDao tipoActivoDao) {
        this.tipoActivoDao = tipoActivoDao;
    }
    
    
    
    @Override
    public void create(TTipoActivo tipoActivo) {
    this.tipoActivoDao.create(tipoActivo);
    }

    @Override
    public TTipoActivo getActivo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TTipoActivo> listTipoActivo(Integer id) {
    return this.tipoActivoDao.listTipoActivo(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TTipoActivo tipoActivo) {
    this.tipoActivoDao.update(tipoActivo);
    }

    @Override
    public TTipoActivo getActivoRepAct(Integer idEnt, Integer idTip, String codigo) {
       return  this.tipoActivoDao.getActivoRepAct(idEnt, idTip, codigo);
    }

    @Override
    public TTipoActivo getActivoRep(Integer idEnt, String codigo) {
       return this.tipoActivoDao.getActivoRep(idEnt, codigo);
    }
    
}
