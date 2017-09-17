/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ICooperativaBo;
import com.sigaf.Idao.ICooperativaDao;
import com.sigaf.pojo.TCooperativa;
import java.util.List;

/**
 *
 * @author yonat
 */
public class CooperativaBo implements ICooperativaBo {
    
    private ICooperativaDao icooperativaDao;

    public ICooperativaDao getIcooperativaDao() {
        return icooperativaDao;
    }

    public void setIcooperativaDao(ICooperativaDao icooperativaDao) {
        this.icooperativaDao = icooperativaDao;
    }

    @Override
    public void create(TCooperativa coopertativa) {
    
     this.icooperativaDao.create(coopertativa);
    
    }

    @Override
    public TCooperativa getTCooperativa(Integer id) {
    return this.icooperativaDao.getTCooperativa(id);
    }

    @Override
    public List<TCooperativa> listTCooperativas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public void update(TCooperativa cooperativa) {
    this.icooperativaDao.update(cooperativa);
    }

    @Override
    public void delete(TCooperativa cooperativa) {
       this.icooperativaDao.delete(cooperativa);
    
    }
    
}
