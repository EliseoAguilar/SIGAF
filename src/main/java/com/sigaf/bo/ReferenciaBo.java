/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IReferenciaBo;
import com.sigaf.Idao.IReferenciaDao;
import com.sigaf.pojo.TReferencia;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ReferenciaBo implements IReferenciaBo {
    
    IReferenciaDao ireferenciaDao;
    
    public IReferenciaDao getIreferenciaDao() {
        return ireferenciaDao;
    }
    
    public void setIreferenciaDao(IReferenciaDao ireferenciaDao) {
        this.ireferenciaDao = ireferenciaDao;
    }
    
    @Override
    public void create(TReferencia Referencia) {
        this.ireferenciaDao.create(Referencia);
    }
    
    @Override
    public TReferencia getReferencia(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TReferencia> listReferencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TReferencia Referencia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TReferencia> listReferencia(Integer id) {
        
        return this.ireferenciaDao.listReferencia(id);
        
    }
    
    @Override
    public void delete(TReferencia referencia) {
        this.ireferenciaDao.delete(referencia);
        
    }
    
}
