/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.ITipoCultivoBo;
import com.sigaf.Idao.ITipoCultivoDao;
import com.sigaf.pojo.TTipoCultivo;
import java.util.List;

/**
 *
 * @author yonat
 */
public class TipoCultivoBo implements ITipoCultivoBo {

    private ITipoCultivoDao tipoCultivoDao;

    public ITipoCultivoDao getTipoCultivoDao() {
        return tipoCultivoDao;
    }

    public void setTipoCultivoDao(ITipoCultivoDao tipoCultivoDao) {
        this.tipoCultivoDao = tipoCultivoDao;
    }

    @Override
    public void create(TTipoCultivo TipoCultivo) {
        this.tipoCultivoDao.create(TipoCultivo);
    }

    @Override
    public TTipoCultivo getAreaCultivo(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TTipoCultivo> listTipoCultivo() {
    return this.tipoCultivoDao.listTipoCultivo();
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TTipoCultivo TipoCultivo) {
     this.tipoCultivoDao.update(TipoCultivo);
    }

    @Override
    public boolean getTTipoCultivoNombre(String nombre) {
    
    return this.tipoCultivoDao.getTTipoCultivoNombre(nombre);
    }

}
