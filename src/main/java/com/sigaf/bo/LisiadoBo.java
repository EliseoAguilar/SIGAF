/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ILisiadoBo;
import com.sigaf.Idao.ILisiadoDao;
import com.sigaf.pojo.TLisiado;
import java.util.List;

/**
 *
 * @author Genovés
 */
public class LisiadoBo implements ILisiadoBo {
    
    ILisiadoDao ilisiadoDao;
    
    public ILisiadoDao getIlisiadoDao() {
        return ilisiadoDao;
    }
    
    public void setIlisiadoDao(ILisiadoDao ilisiadoDao) {
        this.ilisiadoDao = ilisiadoDao;
    }
    
    @Override
    public void create(TLisiado lisiado) {
        this.ilisiadoDao.create(lisiado);
    }
    
    @Override
    public TLisiado getLisiado(Integer id) {
        return this.ilisiadoDao.getLisiado(id);        
    }
    
    @Override
    public List<TLisiado> listTLisiado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TLisiado lisiado) {
        this.ilisiadoDao.update(lisiado);
        
    }
    
    @Override
    public void delete(TLisiado lisiado) {
        this.ilisiadoDao.delete(lisiado);
        
    }
    
}
