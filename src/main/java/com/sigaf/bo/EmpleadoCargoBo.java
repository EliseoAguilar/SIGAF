/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IEmpleadoCargoBo;
import com.sigaf.Idao.IEmpleadoCargoDao;
import com.sigaf.pojo.TEmpleadoCargo;
import java.util.List;

/**
 *
 * @author Genoves
 */
public class EmpleadoCargoBo implements IEmpleadoCargoBo {

    private IEmpleadoCargoDao iempleadoCargoDao;

    public IEmpleadoCargoDao getIempleadoCargoDao() {
        return iempleadoCargoDao;
    }

    public void setIempleadoCargoDao(IEmpleadoCargoDao iempleadoCargoDao) {
        this.iempleadoCargoDao = iempleadoCargoDao;
    }

    
    
    @Override
    public void create(TEmpleadoCargo empleadoCargo) {
        iempleadoCargoDao.create(empleadoCargo);
    }

    @Override
    public TEmpleadoCargo getTEmpleadoCargo(Integer id, Integer idEmpleado) {
        return this.iempleadoCargoDao.getTEmpleadoCargo(id, idEmpleado);
    }
    
    

    @Override
    public List<TEmpleadoCargo> listTEmpleadoCargo(Integer id) {
       return this.iempleadoCargoDao.listTEmpleadoCargo(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEmpleadoCargo empleadoCargo) {
        this.iempleadoCargoDao.update(empleadoCargo);
    }
    
}
