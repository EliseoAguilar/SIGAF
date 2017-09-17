/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ICargoBo;
import com.sigaf.Idao.ICargoDao;
import com.sigaf.pojo.TCargo;
import java.util.List;

/**
 *
 * @author Genoves
 */
public class CargoBo implements ICargoBo{

    
    private ICargoDao cargoDao ;

    public ICargoDao getCargoDao() {
        return cargoDao;
    }

    public void setCargoDao(ICargoDao cargoDao) {
        this.cargoDao = cargoDao;
    }
    
    @Override
    public void create(TCargo cargo) {
        this.cargoDao.create(cargo);
    
    }

    @Override
    public TCargo getCargo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TCargo> listCargo(Integer id) {
        return this.cargoDao.listTCargo(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TCargo cargo) {
        this.cargoDao.update(cargo); 
    }
    
    
}
