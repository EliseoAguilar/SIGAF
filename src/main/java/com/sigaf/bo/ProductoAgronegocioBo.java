/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IProductoAgronegocioBo;
import com.sigaf.Idao.IProductoAgronegocioDao;
import com.sigaf.pojo.TProductoAgronegocio;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ProductoAgronegocioBo implements IProductoAgronegocioBo {
    
    IProductoAgronegocioDao iproductoAgronegocioDao;

    public IProductoAgronegocioDao getIproductoAgronegocioDao() {
        return iproductoAgronegocioDao;
    }

    public void setIproductoAgronegocioDao(IProductoAgronegocioDao iproductoAgronegocioDao) {
        this.iproductoAgronegocioDao = iproductoAgronegocioDao;
    }

    @Override
    public void create(TProductoAgronegocio ProductoAgronegocio) {
    
        this.iproductoAgronegocioDao.create(ProductoAgronegocio);
    }

    @Override
    public TProductoAgronegocio getProductoAgronegocio(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getTProductorAgronegocioNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProductoAgronegocio> listProductoAgronegocio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TProductoAgronegocio ProductoAgronegocio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProductoAgronegocio> listProductoAgronegocio(Integer id) {
   return  this.iproductoAgronegocioDao.listProductoAgronegocio(id);
    
    }
    
}
