/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IProyectoBo;
import com.sigaf.Idao.IProyectoDao;
import com.sigaf.pojo.TProyecto;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ProyectoBo implements IProyectoBo {

    private IProyectoDao iproyectoDao;

    public IProyectoDao getIproyectoDao() {
        return iproyectoDao;
    }

    public void setIproyectoDao(IProyectoDao iproyectoDao) {
        this.iproyectoDao = iproyectoDao;
    }

    @Override
    public void create(TProyecto proyecto) {
        this.iproyectoDao.create(proyecto);
    }

    @Override
    public TProyecto getTProyecto(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProyecto> listTProyecto() {

        return this.iproyectoDao.listTProyecto();

    }

    
    @Override
    public void update(TProyecto proyecto) {
        this.iproyectoDao.update(proyecto);
    }

    @Override
    public Integer getCorrelativo() {
        return this.iproyectoDao.getCorrelativo();
    }

    @Override
    public Integer getCorrelativoProyecto() {
        return this.iproyectoDao.getCorrelativoProyecto();

    }

    @Override
    public List<TProyecto> listTProyectoAprobados() {
    
    return this.iproyectoDao.listTProyectoAprobados();
    }

    @Override
    public void delete(TProyecto proyecto) {
    
        
        this.iproyectoDao.delete(proyecto);
    
    
    
    
    }

    @Override
    public List<TProyecto> listTProyecto(Integer id) {
       return  this.iproyectoDao.listTProyecto(id);
    }

    @Override
    public List<TProyecto> listTCreditos() {
       return  this.iproyectoDao.listTCreditos();
    }

}
