/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IProductoCooperativaBo;
import com.sigaf.Ibo.IProductoProductorBo;
import com.sigaf.Idao.IProductoCooperativaDao;
import com.sigaf.pojo.TProductoCooperativa;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ProductoCooperativaBo implements IProductoCooperativaBo {

    IProductoCooperativaDao iproductoCooperativaDao;

    public IProductoCooperativaDao getIproductoCooperativaDao() {
        return iproductoCooperativaDao;
    }

    public void setIproductoCooperativaDao(IProductoCooperativaDao iproductoCooperativaDao) {
        this.iproductoCooperativaDao = iproductoCooperativaDao;
    }

    @Override
    public void create(TProductoCooperativa ProductoCooperativa) {

        this.iproductoCooperativaDao.create(ProductoCooperativa);

    }

    @Override
    public TProductoCooperativa getProductoCooperativa(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getTProductorCooperativaNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProductoCooperativa> listProductoCooperativa() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TProductoCooperativa ProductoCooperativa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
