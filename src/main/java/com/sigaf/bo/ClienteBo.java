/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IClienteBo;
import com.sigaf.Idao.IClienteDao;
import com.sigaf.pojo.TCliente;
import java.util.List;

/**
 *
 * @author yonat
 */
public class ClienteBo implements IClienteBo {

    IClienteDao iclienteDao;

    public IClienteDao getIclienteDao() {
        return iclienteDao;
    }

    public void setIclienteDao(IClienteDao iclienteDao) {
        this.iclienteDao = iclienteDao;
    }

    @Override
    public void create(TCliente Agronegocio) {
        this.iclienteDao.create(Agronegocio);

    }

    @Override
    public TCliente getCliente(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TCliente> listCliente() {
        return this.iclienteDao.listCliente();

    }

    @Override
    public void update(TCliente Cliente) {
        this.iclienteDao.update(Cliente);

    }

    @Override
    public List<TCliente> listClienteCodigo() {
        return this.iclienteDao.listClienteCodigo();
    }

    @Override
    public void delete(TCliente cliente) {
        this.iclienteDao.update(cliente);
    }

    @Override
    public Boolean getDuiCliente(String dui) {
        return this.iclienteDao.getDuiCliente(dui);

    }

    @Override
    public Boolean getNitCliente(String nit) {
    return this.iclienteDao.getNitCliente(nit);
    }

}
