/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TProductoCooperativa;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IProductoCooperativaBo {
    
    public void create(TProductoCooperativa ProductoCooperativa);

    public TProductoCooperativa getProductoCooperativa(Integer id);
    
    public boolean getTProductorCooperativaNombre(String nombre);

    public List<TProductoCooperativa> listProductoCooperativa();

    public void delete(Integer id);

    public void update(TProductoCooperativa ProductoCooperativa);

    
}
