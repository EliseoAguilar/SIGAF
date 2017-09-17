/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IProductorGrupalBo;
import com.sigaf.Idao.IProductorGrupalDao;
import com.sigaf.pojo.TProductorGrupal;
import java.util.List;

/**
 * 
 * @author yonat
 */
public class ProductorGrupalBo implements IProductorGrupalBo {
    
    private IProductorGrupalDao productorGrupalDao;
    
    public IProductorGrupalDao getProductorGrupalDao() {
        return productorGrupalDao;
    }
    
    public void setProductorGrupalDao(IProductorGrupalDao productorGrupalDao) {
        this.productorGrupalDao = productorGrupalDao;
    }
    
    @Override
    public void create(TProductorGrupal ProductorGrupal) {
        this.productorGrupalDao.create(ProductorGrupal);
    }
    
    @Override
    public TProductorGrupal getProductorGrupal(Integer id) {
    return this.productorGrupalDao.getProductorGrupal(id);
    }
    
    @Override
    public List<TProductorGrupal> listProductorGrupal() {
        
        return this.productorGrupalDao.listProductorGrupal();        
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TProductorGrupal ProductorGrupal) { 
     this.productorGrupalDao.update(ProductorGrupal);
    }

    @Override
    public boolean getTProductorGrupalNombre(String nombre) {
    return this.productorGrupalDao.getTProductorGrupalNombre(nombre);
    
    }
    
}
