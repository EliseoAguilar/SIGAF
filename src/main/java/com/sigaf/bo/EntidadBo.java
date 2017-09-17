/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Idao.IEntidadDao;
import com.sigaf.pojo.TEntidad;
import java.util.List;

/**
 *
 * @author Genoves
 */
public class EntidadBo implements IEntidadBo {

    private IEntidadDao ientidadDao;

    public IEntidadDao getIentidadDao() {
        return ientidadDao;
    }

    public void setIentidadDao(IEntidadDao ientidadDao) {
        this.ientidadDao = ientidadDao;
    }

    @Override
    public void create(TEntidad entidad) {
        this.ientidadDao.create(entidad);

    }

    @Override
    public TEntidad getTEntidad(Integer id) {
        return this.ientidadDao.getTEntidad(id);

    }

    @Override
    public List<TEntidad> listTEndidadTodos() {
        return ientidadDao.listTEndidadTodos();
    }

    @Override
    public List<TEntidad> listTEndidad() {
        return ientidadDao.listTEndidad();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEntidad entidad) {
        this.ientidadDao.update(entidad);
    }

    @Override
    public boolean getTEntidadCodigo(String codigo, String codigoAux) {

        return this.ientidadDao.getTEntidadCodigo(codigo, codigoAux);
    }

    @Override
    public List<TEntidad> listTEndidadCodigo() {
    return this.ientidadDao.listTEndidadCodigo();
    }

}
