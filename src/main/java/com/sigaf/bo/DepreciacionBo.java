/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IDepreciacionBo;
import com.sigaf.Idao.IDepreciacionDao;
import com.sigaf.pojo.TDepreciacion;
import java.util.List;

/**
 *
 * @author Eliseo Aguilar
 */
public class DepreciacionBo implements IDepreciacionBo {

    private IDepreciacionDao depreciacionDao;

    public IDepreciacionDao getDepreciacionDao() {
        return depreciacionDao;
    }

    public void setDepreciacionDao(IDepreciacionDao depreciacionDao) {
        this.depreciacionDao = depreciacionDao;
    }

    @Override
    public void create(TDepreciacion depreciacion) {
        this.depreciacionDao.create(depreciacion);
    }

    @Override
    public TDepreciacion getDepreciacion(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TDepreciacion> listDepreciacion(Integer id) {
        return this.depreciacionDao.listDepreciacion(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TDepreciacion depreciacion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
