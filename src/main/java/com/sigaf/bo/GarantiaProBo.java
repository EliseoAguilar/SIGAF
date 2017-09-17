/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IGarantiaProBo;
import com.sigaf.Idao.IGarantiaProDao;
import com.sigaf.pojo.TGarantia;
import java.util.List;

/**
 *
 * @author Genovés
 */
public class GarantiaProBo implements IGarantiaProBo {

    private IGarantiaProDao igarantiaProDao;

    public IGarantiaProDao getIgarantiaProDao() {
        return igarantiaProDao;
    }

    public void setIgarantiaProDao(IGarantiaProDao igarantiaProDao) {
        this.igarantiaProDao = igarantiaProDao;
    }

    @Override
    public void create(TGarantia garantia) {

        this.igarantiaProDao.create(garantia);
    }

    @Override
    public TGarantia getTGarantia(Integer id) {
       return this.igarantiaProDao.getTGarantia(id);
    }

    @Override
    public List<TGarantia> listTGarantia(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TGarantia garantia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
