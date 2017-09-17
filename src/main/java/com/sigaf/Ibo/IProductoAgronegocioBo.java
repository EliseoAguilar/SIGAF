/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TProductoAgronegocio;
import java.util.List;

/**
 *
 * @author yonat
 */
public interface IProductoAgronegocioBo {      
    
    public void create(TProductoAgronegocio ProductoAgronegocio);

    public TProductoAgronegocio getProductoAgronegocio(Integer id);
    
    public boolean getTProductorAgronegocioNombre(String nombre);

    public List<TProductoAgronegocio> listProductoAgronegocio();
    
    public List<TProductoAgronegocio> listProductoAgronegocio(Integer id);

    public void delete(Integer id);

    public void update(TProductoAgronegocio ProductoAgronegocio);    
    
}
