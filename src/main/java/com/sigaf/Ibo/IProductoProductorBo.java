/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TProductoProductor;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IProductoProductorBo {
    
    
    public void create(TProductoProductor productoProductor);

    public TProductoProductor getTProductorProductor(Integer id);
    
    public boolean getTProductoProductorCodigo(String codigo,String codigoAux);

    public List<TProductoProductor> listTProductoProductor(Integer id);
    
    public List<TProductoProductor> listTProductoProductorCod(Integer idproducto);

    public void delete(Integer id);

    public void update(TProductoProductor productoProductor);
    
}
