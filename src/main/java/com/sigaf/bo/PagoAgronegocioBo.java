/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IPagoAgronegocioBo;
import com.sigaf.Idao.IPagoAgronegocioDao;
import com.sigaf.pojo.TPagoAgronegocio;
import java.util.List;

/**
 *
 * @author yonat
 */
public class PagoAgronegocioBo implements IPagoAgronegocioBo {
    
    IPagoAgronegocioDao ipagoAgronegocioDao;
    
    public IPagoAgronegocioDao getIpagoAgronegocioDao() {
        return ipagoAgronegocioDao;
    }
    
    public void setIpagoAgronegocioDao(IPagoAgronegocioDao ipagoAgronegocioDao) {
        this.ipagoAgronegocioDao = ipagoAgronegocioDao;
    }
    
    @Override
    public void create(TPagoAgronegocio PagoAgronegocio) {
        this.ipagoAgronegocioDao.create(PagoAgronegocio);
        
    }
    
    @Override
    public TPagoAgronegocio getPagoAgronegocio(Integer id) {
    
        return this.ipagoAgronegocioDao.getPagoAgronegocio(id);
    }
    
    @Override
    public boolean getTPagoAgronegocio(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<TPagoAgronegocio> listPagoAgronegocio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void update(TPagoAgronegocio PagoAgronegocio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getCorrelativo() {
        return this.ipagoAgronegocioDao.getCorrelativo();
    
    }
    
}
