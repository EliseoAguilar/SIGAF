/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IPagoBo;
import com.sigaf.Idao.IPagoDao;
import com.sigaf.pojo.TPago;
import java.util.List;

/**
 *
 * @author yonat
 */
public class PagoBo implements IPagoBo {
    
    private IPagoDao ipagoDao;
    
    public IPagoDao getIpagoDao() {
        return ipagoDao;
    }
    
    public void setIpagoDao(IPagoDao ipagoDao) {
        this.ipagoDao = ipagoDao;
    }
    
    @Override
    public void create(TPago pago) {
        this.ipagoDao.create(pago);
    }
    
    @Override
    public TPago getTPago(Integer id) {
        return this.ipagoDao.getTPago(id);
    }
    
   
    @Override
    public void update(TPago pago) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TPago> listTPago(Integer id) {
        return this.ipagoDao.listTPago(id);
    }

    @Override
    public Integer getCorrelativo() {
        return this.ipagoDao.getCorrelativo();
    
    }

    @Override
    public void delete(TPago pago) {
    
    this.ipagoDao.delete(pago);
    }
    
}
