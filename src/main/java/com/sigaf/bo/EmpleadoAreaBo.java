/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IEmpleadoAreaBo;
import com.sigaf.Idao.IEmpleadoAreaDao;
import com.sigaf.pojo.TEmpleadoArea;
import java.util.List;

/**
 *
 * @author yonat
 */
public class EmpleadoAreaBo implements IEmpleadoAreaBo {

    private IEmpleadoAreaDao EmpleadoAreaDao;

    public IEmpleadoAreaDao getEmpleadoAreaDao() {
        return EmpleadoAreaDao;
    }

    public void setEmpleadoAreaDao(IEmpleadoAreaDao EmpleadoAreaDao) {
        this.EmpleadoAreaDao = EmpleadoAreaDao;
    }

    @Override
    public void create(TEmpleadoArea empleadoArea) {
        this.EmpleadoAreaDao.create(empleadoArea);
    }

    @Override
    public TEmpleadoArea getTEmpleadoArea(Integer id, Integer idEmpleado) {
       return  this.EmpleadoAreaDao.getTEmpleadoArea(id, idEmpleado);
    }

    @Override
    public List<TEmpleadoArea> listTEmpleadoArea(Integer id) {
        return this.EmpleadoAreaDao.listTEmpleadoArea(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEmpleadoArea empleadoArea) {
        this.EmpleadoAreaDao.update(empleadoArea);
    }

    @Override
    public Boolean correoRepetido(String correo, int idEnt) {
        return this.EmpleadoAreaDao.correoRepetido(correo, idEnt);
    }

}
