/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IProveedorBo;
import com.sigaf.Idao.IProveedorDao;
import com.sigaf.pojo.TProveedor;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class ProveedorBo implements IProveedorBo {

    IProveedorDao proveedorDao;

    public IProveedorDao getProveedorDao() {
        return proveedorDao;
    }

    public void setProveedorDao(IProveedorDao proveedorDao) {
        this.proveedorDao = proveedorDao;
    }

    @Override
    public void create(TProveedor proveedor) {
        this.proveedorDao.create(proveedor);
    }

    @Override
    public TProveedor getProveedor(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TProveedor> listProveedor(Integer id) {
        return this.proveedorDao.listProveedor(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TProveedor proveedor) {
        this.proveedorDao.update(proveedor);
    }

    @Override
    public TProveedor getProveedorRepNit(Integer idEnt, String nit) {
        return this.proveedorDao.getProveedorRepNit(idEnt, nit);
    }

    @Override
    public TProveedor getProveedorRepActNit(Integer idEnt, Integer idProv, String nit) {
        return this.proveedorDao.getProveedorRepActNit(idEnt, idProv, nit);
    }

    @Override
    public TProveedor getProveedorRepNrc(Integer idEnt, String nrc) {
        return this.proveedorDao.getProveedorRepNrc(idEnt, nrc);
    }

    @Override
    public TProveedor getProveedorRepActNrc(Integer idEnt, Integer idProv, String nrc) {
        return this.proveedorDao.getProveedorRepActNrc(idEnt, idProv, nrc);
    }
    
          @Override
    public TProveedor getProveedorRepCorreo(Integer idEnt, String correo) {
      
       return this.proveedorDao.getProveedorRepCorreo(idEnt,  correo); 
    }

    @Override
    public TProveedor getProveedorRepActCorreo(Integer idEnt, Integer idProv, String correo) {
      
       return this.proveedorDao.getProveedorRepActCorreo(idEnt, idProv, correo);
    }

}
