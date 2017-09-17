/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.Ibo;

import com.sigaf.pojo.TProveedor;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public interface IProveedorBo {

    public void create(TProveedor proveedor);

    public TProveedor getProveedor(Integer id);

    public TProveedor getProveedorRepNit(Integer idEnt, String nit);

    public TProveedor getProveedorRepActNit(Integer idEnt, Integer idProv, String nit);

    public TProveedor getProveedorRepNrc(Integer idEnt, String nrc);

    public TProveedor getProveedorRepActNrc(Integer idEnt, Integer idProv, String nrc);

    public List<TProveedor> listProveedor(Integer id);

    public void delete(Integer id);

    public void update(TProveedor proveedor);
    
     public TProveedor getProveedorRepCorreo(Integer idEnt, String correo);
     
      public TProveedor getProveedorRepActCorreo(Integer idEnt, Integer idProv, String correo);
}
