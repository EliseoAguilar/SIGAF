/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IEstructuraBo;
import com.sigaf.Idao.IEstructuraDao;

import com.sigaf.pojo.TEstructura;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class EstructuraBo implements IEstructuraBo{

    IEstructuraDao  estructuraDao;

    public IEstructuraDao getEstructuraDao() {
        return estructuraDao;
    }

    public void setEstructuraDao(IEstructuraDao estructuraDao) {
        this.estructuraDao = estructuraDao;
    }

    @Override
    public void create(TEstructura estructura) {
    this.estructuraDao.create(estructura);
    }

    @Override
    public TEstructura getEstructura(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TEstructura> listEstructura(Integer idEjercicio, Integer idTipo) {
    return this.estructuraDao.listEstructura(idEjercicio, idTipo);
    }

    @Override
    public void delete(Integer id, Integer tipoRep) {
        
    this.estructuraDao.delete(id,tipoRep);
    
    }

    @Override
    public void update(TEstructura estructura) {
         this.estructuraDao.update(estructura);
    }
    

   
    
}
