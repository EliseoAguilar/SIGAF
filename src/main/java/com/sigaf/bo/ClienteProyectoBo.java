/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IClienteProyectoBo;
import com.sigaf.Idao.IClienteProyectoDao;
import com.sigaf.pojo.TClienteProyecto;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ClienteProyectoBo implements IClienteProyectoBo {

    IClienteProyectoDao iclienteProyectoDao;

    public IClienteProyectoDao getIclienteProyectoDao() {
        return iclienteProyectoDao;
    }

    public void setIclienteProyectoDao(IClienteProyectoDao iclienteProyectoDao) {
        this.iclienteProyectoDao = iclienteProyectoDao;
    }

    @Override
    public void create(TClienteProyecto ClienteProyecto) {

        this.iclienteProyectoDao.create(ClienteProyecto);

    }

    @Override
    public TClienteProyecto getClienteProyecto(Integer id) {
      return  this.iclienteProyectoDao.getClienteProyecto(id);
    
    }

    @Override
    public List<TClienteProyecto> listClienteProyecto() {
        return this.iclienteProyectoDao.listClienteProyecto();
    }

   

    @Override
    public void update(TClienteProyecto ClienteProyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TClienteProyecto> listTClienteProyecto(Integer id) {
        return this.iclienteProyectoDao.listTClienteProyecto(id);
    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoResolucion(Integer id) {

        return this.iclienteProyectoDao.listTClienteProyectoResolucion(id);

    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoAprovados(Integer id) {
        return this.iclienteProyectoDao.listTClienteProyectoAprovados(id);
    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoAprovadosComprabacion(Integer id) {
        return this.iclienteProyectoDao.listTClienteProyectoAprovadosComprabacion(id);
    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoAprovadosEjecutandose() {
        return this.iclienteProyectoDao.listTClienteProyectoAprovadosEjecutandose();
    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoNoAprobados(Integer id) {
        return this.iclienteProyectoDao.listTClienteProyectoNoAprobados(id);
    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoTodas(Integer id) {
    return this.iclienteProyectoDao.listTClienteProyectoTodas(id);
    }

    @Override
    public List<TClienteProyecto> listTClienteProyectoEjecutandose(Integer id) {
    return this.iclienteProyectoDao.listTClienteProyectoEjecutandose(id);
    }

    @Override
    public void delete(TClienteProyecto clienteProyecto) {
    
       this.iclienteProyectoDao.delete(clienteProyecto);
    
    }

}
