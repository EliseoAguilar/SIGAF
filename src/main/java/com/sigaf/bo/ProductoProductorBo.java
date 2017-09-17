/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IProductoProductorBo;
import com.sigaf.Idao.IProductoProductorDao;
import com.sigaf.pojo.TProductoProductor;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ProductoProductorBo implements IProductoProductorBo {

    IProductoProductorDao iproductroProductorDao;

    public IProductoProductorDao getIproductroProductorDao() {
        return iproductroProductorDao;
    }

    public void setIproductroProductorDao(IProductoProductorDao iproductroProductorDao) {
        this.iproductroProductorDao = iproductroProductorDao;
    }

    @Override
    public void create(TProductoProductor productoProductor) {
        this.iproductroProductorDao.create(productoProductor);

    }

    @Override
    public TProductoProductor getTProductorProductor(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getTProductoProductorCodigo(String codigo, String codigoAux) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TProductoProductor productoProductor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProductoProductor> listTProductoProductor(Integer id) {
        return this.iproductroProductorDao.listTProductoProductor(id);
    }

    @Override
    public List<TProductoProductor> listTProductoProductorCod(Integer idproducto) {
        return this.iproductroProductorDao.listTProductoProductorCod(idproducto);

    }

}
