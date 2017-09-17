/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IAgropecuarioBo;
import com.sigaf.Idao.IAgropecuarioDao;
import com.sigaf.pojo.TAgropecuario;
import java.util.List;

/**
 *
 * @author yonat
 */
public class AgropecuarioBo implements IAgropecuarioBo {
    
    IAgropecuarioDao iagropecuarioDao;
    
    public IAgropecuarioDao getIagropecuarioDao() {
        return iagropecuarioDao;
    }
    
    public void setIagropecuarioDao(IAgropecuarioDao iagropecuarioDao) {
        this.iagropecuarioDao = iagropecuarioDao;
    }
    
    @Override
    public void create(TAgropecuario Agropecuario) {
        this.iagropecuarioDao.create(Agropecuario);
    }
    
    @Override
    public TAgropecuario getAgropecuario(Integer id) {
    return this.iagropecuarioDao.getAgropecuario(id);
    
    }
    
    @Override
    public List<TAgropecuario> listAgropecuario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    
    @Override
    public void update(TAgropecuario Agropecuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(TAgropecuario agropecuario) {
    this.iagropecuarioDao.delete(agropecuario);
    
    }
    
}
