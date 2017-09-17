/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IDesembolsoBo;
import com.sigaf.Idao.IDesembolsoDao;
import com.sigaf.pojo.TDesembolso;
import java.util.List;

/**
 *
 * @author yonat
 */
public class DesembolsoBo implements IDesembolsoBo {

    private IDesembolsoDao idesembolsoDao;

    public IDesembolsoDao getIdesembolsoDao() {
        return idesembolsoDao;
    }

    public void setIdesembolsoDao(IDesembolsoDao idesembolsoDao) {
        this.idesembolsoDao = idesembolsoDao;
    }

    @Override
    public void create(TDesembolso desembolso) {
        this.idesembolsoDao.create(desembolso);
    }

    @Override
    public TDesembolso getDesembolso(Integer id) {
        return this.idesembolsoDao.getDesembolso(id);    
    }

    @Override
    public List<TDesembolso> listDesembolso() {
        return this.idesembolsoDao.listDesembolso();
    }

  
    @Override
    public void update(TDesembolso desembolso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TDesembolso> listDesembolso(Integer id) {
    return this.idesembolsoDao.listDesembolso(id);
    
    }

    @Override
    public void delete(TDesembolso desembolso) {
    
        this.idesembolsoDao.delete(desembolso);
    
    }

}
