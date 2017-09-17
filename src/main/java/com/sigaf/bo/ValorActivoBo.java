/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IValorActivoBo;
import com.sigaf.Idao.IValorActivoDao;
import com.sigaf.pojo.TValorActivo;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class ValorActivoBo implements IValorActivoBo{

    private IValorActivoDao valorActivoDao;

    public IValorActivoDao getValorActivoDao() {
        return valorActivoDao;
    }

    public void setValorActivoDao(IValorActivoDao valorActivoDao) {
        this.valorActivoDao = valorActivoDao;
    }
    
    
    
    @Override
    public void create(TValorActivo valor) {
    valorActivoDao.create(valor);
        
        
    }

    @Override
    public TValorActivo getTValorActivo(Integer id) {
        return this.valorActivoDao.getTValorActivo(id) ;
    }

    @Override
    public List<TValorActivo> listTValorActivo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TValorActivo valor) {
    valorActivoDao.update(valor);
    }
    
}
