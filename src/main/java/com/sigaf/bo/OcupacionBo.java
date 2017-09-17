/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IOcupacionBo;
import com.sigaf.Idao.IOcupacionDao;
import com.sigaf.pojo.TOcupacion;
import java.util.List;

/**
 *
 * @author Genoves
 */
public class OcupacionBo implements IOcupacionBo {
    
    private IOcupacionDao iocupacionDao;

    public IOcupacionDao getIocupacionDao() {
        return iocupacionDao;
    }

    public void setIocupacionDao(IOcupacionDao iocupacionDao) {
        this.iocupacionDao = iocupacionDao;
    }
    
    
    

    @Override
    public void create(TOcupacion ocupacion) {
       iocupacionDao.create(ocupacion);
    }

    @Override
    public TOcupacion getTOcupacion(Integer id, Integer idEmpleado) {
        return this.iocupacionDao.getTOcupacion(id, idEmpleado);
    }

    @Override
    public List<TOcupacion> listTOcupacion(Integer id) {
        return this.iocupacionDao.listTOcupacion(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TOcupacion ocupacion) {
        this.iocupacionDao.update(ocupacion);
    }
    
}
