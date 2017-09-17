/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IAgronegocioBo;
import com.sigaf.Idao.IAgronegocioDao;
import com.sigaf.pojo.TAgronegocio;
import java.util.List;

/**
 *
 * @author yonat
 */
public class AgronegocioBo implements IAgronegocioBo {

    IAgronegocioDao iagronegocioDao;

    public IAgronegocioDao getIagronegocioDao() {
        return iagronegocioDao;
    }

    public void setIagronegocioDao(IAgronegocioDao iagronegocioDao) {
        this.iagronegocioDao = iagronegocioDao;
    }

    @Override
    public void create(TAgronegocio Agronegocio) {
        this.iagronegocioDao.create(Agronegocio);

    }

    @Override
    public TAgronegocio getAgronegocio(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TAgronegocio> listAgronegocio() {
        return this.iagronegocioDao.listAgronegocio();

    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TAgronegocio Agronegocio) {
    this.iagronegocioDao.update(Agronegocio);
    }

    @Override
    public Integer getCorrelativo() {
        return this.iagronegocioDao.getCorrelativo();
    }

    @Override
    public List<TAgronegocio> listAgronegocioCodigo(String codigo) {

        return this.iagronegocioDao.listAgronegocioCodigo(codigo);
    }

    @Override
    public List<TAgronegocio> listAgronegocioDui(String codigo) {

        return this.iagronegocioDao.listAgronegocioDui(codigo);

    }

}
