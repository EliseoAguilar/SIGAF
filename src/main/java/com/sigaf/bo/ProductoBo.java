/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IProductoBo;
import com.sigaf.Idao.IProductoDao;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TProducto;
import com.sigaf.pojo.TProductorIndividual;
import com.sigaf.pojo.TTipoCultivo;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ProductoBo implements IProductoBo {
    
    private IProductoDao iproductoDao;
    
    public IProductoDao getIproductoDao() {
        return iproductoDao;
    }
    
    public void setIproductoDao(IProductoDao iproductoDao) {
        this.iproductoDao = iproductoDao;
    }
    
    @Override
    public void create(TProducto entidad) {
        this.iproductoDao.create(entidad);
        
    }
    
    @Override
    public TProducto getTProducto(Integer id) {
        return this.iproductoDao.getTProducto(id);
        
    }
    
    @Override
    public boolean getTProductoCodigo(String codigo, String codigoAux) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TProducto> listTProducto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TProducto entidad) {
        
        this.iproductoDao.update(entidad);
    }
    
    @Override
    public List<TProductorIndividual> listTProductorIndividual() {
        return this.iproductoDao.listTProductorIndividual();
    }
    
    @Override
    public List<TAreaCultivo> listTAreaCultivo() {
        return this.iproductoDao.listTAreaCultivo();
    }
    
    @Override
    public List<TTipoCultivo> listTTipoCultivo(Integer id) {
        return this.iproductoDao.listTTipoCultivo(id);
    }
    
    @Override
    public TTipoCultivo getTCultivo(Integer id) {
        return this.iproductoDao.getTCultivo(id);
    }
    
    @Override
    public TAreaCultivo getTAreaCultivo(Integer id) {
        return this.iproductoDao.getTAreaCultivo(id);
    }
    
}
