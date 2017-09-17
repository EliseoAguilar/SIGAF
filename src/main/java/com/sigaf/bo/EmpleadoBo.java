/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IEmpleadoBo;
import com.sigaf.Idao.IEmpleadoDao;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoCargo;
import java.util.List;

/**
 *
 * @author Genoves
 */
public class EmpleadoBo implements IEmpleadoBo {

    private IEmpleadoDao iempleadoDao;

    public IEmpleadoDao getIempleadoDao() {
        return iempleadoDao;
    }

    public void setIempleadoDao(IEmpleadoDao iempleadoDao) {
        this.iempleadoDao = iempleadoDao;
    }

    @Override
    public void create(TEmpleado empleado) {
        this.iempleadoDao.create(empleado);
    }

    @Override
    public TEmpleado getTEmpleado(Integer id) {
    return this.iempleadoDao.getTEmpleado(id);
    
    }

    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEmpleado empleado) {
        this.iempleadoDao.update(empleado);
    }

    @Override
    public List<TEmpleadoCargo> listTEmpleadoCargo(Integer id) {
        return iempleadoDao.listTEmpleadoCargo(id);
    }

    @Override
    public Boolean getDUiTEmpleado(String dui) {
        return iempleadoDao.getDUiTEmpleado(dui);
    }

    @Override
    public List<TEmpleado> listEmpleadosOrSocios(Integer idEmpleadoArea, Integer tipo){
        return iempleadoDao.listEmpleadosOrSocios(idEmpleadoArea,  tipo);

    }

   @Override
    public List<TEmpleado> listTEmpleado() {
        
        return this.iempleadoDao.listTEmpleado();
    }

    @Override
    public List<TEmpleado> listEmpleadosActivos(Integer idEmpleadoArea) {
    return this.iempleadoDao.listEmpleadosActivos(idEmpleadoArea);
    
    }

   

}
