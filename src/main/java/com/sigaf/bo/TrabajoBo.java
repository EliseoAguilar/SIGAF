/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ITrabajoBo;
import com.sigaf.Idao.ITrabajoDao;
import com.sigaf.pojo.TTrabajo;
import java.util.List;

/**
 *
 * @author yonat
 */
public class TrabajoBo implements ITrabajoBo {

    ITrabajoDao itrabajoDao;

    public ITrabajoDao getItrabajoDao() {
        return itrabajoDao;
    }

    public void setItrabajoDao(ITrabajoDao itrabajoDao) {
        this.itrabajoDao = itrabajoDao;
    }

    @Override
    public void create(TTrabajo trabajo) {
        this.itrabajoDao.create(trabajo);
    }

    @Override
    public TTrabajo getTrabajo(Integer id) {
    
        return this.itrabajoDao.getTrabajo(id);
    }

    @Override
    public List<TTrabajo> listTrabajo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TTrabajo trabajo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TTrabajo trabajo) {
    
    this.itrabajoDao.update(trabajo);
    }

}
