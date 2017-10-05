/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IEntidadProyectoBo;
import com.sigaf.Idao.IEntidadProyectoDao;
import com.sigaf.pojo.TEntidadProyecto;
import java.util.List;

/**
 *
 * @author yonat
 */
public class EntidadProyectoBo implements IEntidadProyectoBo {

    private IEntidadProyectoDao ientidadProyectoDao;

    public IEntidadProyectoDao getIentidadProyectoDao() {
        return ientidadProyectoDao;
    }

    public void setIentidadProyectoDao(IEntidadProyectoDao ientidadProyectoDao) {
        this.ientidadProyectoDao = ientidadProyectoDao;
    }

    @Override
    public void create(TEntidadProyecto entidadProyecto) {
        this.ientidadProyectoDao.create(entidadProyecto);

    }

    @Override
    public TEntidadProyecto getTEntidadProyecto(Integer id) {
        return this.ientidadProyectoDao.getTEntidadProyecto(id);
    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectos() {
        return this.ientidadProyectoDao.listTEndidadProyectos();
    }

  
    @Override
    public void update(TEntidadProyecto entidad) {
    this.ientidadProyectoDao.update(entidad);
    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectos(Integer id) {
        return this.ientidadProyectoDao.listTEndidadProyectos(id);
    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosResolucion(Integer id) {
        return this.ientidadProyectoDao.listTEndidadProyectosResolucion(id);

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosAprovados(Integer id) {

        return this.ientidadProyectoDao.listTEndidadProyectosAprovados(id);

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosAprovadosClinte(Integer id) {

        return this.ientidadProyectoDao.listTEndidadProyectosAprovadosClinte(id);

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosNoAprobados() {

        return this.ientidadProyectoDao.listTEndidadProyectosNoAprobados();

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosTodas(Integer id) {
        return this.ientidadProyectoDao.listTEndidadProyectosTodas(id);

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosEjecutandose(Integer id) {
        return this.ientidadProyectoDao.listTEndidadProyectosEjecutandose(id);

    }

    @Override
    public List<TEntidadProyecto> listTEndidadProyectosAprovadosEjecutandose(Integer id) {
        return this.ientidadProyectoDao.listTEndidadProyectosAprovadosEjecutandose(id);
    }

    @Override
    public void delete(TEntidadProyecto entidadProyecto) {
        
        this.ientidadProyectoDao.delete(entidadProyecto);
            
           
            
            }

}
