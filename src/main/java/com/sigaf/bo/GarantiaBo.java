/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IGarantiaBo;
import com.sigaf.Idao.IGarantiaDao;
import com.sigaf.pojo.TGarantia;
import java.util.List;

/**
 *
 * @author yonat
 */
public class GarantiaBo implements IGarantiaBo {

    private IGarantiaDao igarantiaDao;

    public IGarantiaDao getIgarantiaDao() {
        return igarantiaDao;
    }

    public void setIgarantiaDao(IGarantiaDao igarantiaDao) {
        this.igarantiaDao = igarantiaDao;
    }

    @Override
    public void create(TGarantia garantia) {

        this.igarantiaDao.create(garantia);
    }

    @Override
    public TGarantia getTGarantia(Integer id) {
        return this.igarantiaDao.getTGarantia(id);
    
    }

    @Override
    public List<TGarantia> listTGarantia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public void update(TGarantia entidad) {
        this.igarantiaDao.update(entidad);
    }

    @Override
    public List<TGarantia> listTGarantia(Integer id) {
     return  this.igarantiaDao.listTGarantia(id);
    }

    @Override
    public void delete(TGarantia garantia) {
    this.igarantiaDao.delete(garantia);
    
    
    }

}
