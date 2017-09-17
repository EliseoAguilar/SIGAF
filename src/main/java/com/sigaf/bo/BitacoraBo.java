/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Idao.IBitacoraDao;
import com.sigaf.pojo.TBitacora;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class BitacoraBo implements IBitacoraBo{

    private IBitacoraDao bitacoraDao  ;

    public IBitacoraDao getBitacoraDao() {
        return bitacoraDao;
    }

    public void setBitacoraDao(IBitacoraDao bitacoraDao) {
        this.bitacoraDao = bitacoraDao;
    }
    
    
    
    
    @Override
    public void create(TBitacora bitacora) {
        bitacoraDao.create(bitacora);
    }

    @Override
    public TBitacora getBitacora(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TBitacora> listBitacoraFechas(Integer id, Date fecha1, Date fecha2) {
    return bitacoraDao.listBitacoraFechas(id, fecha1, fecha2);
    }

    @Override
    public List<TBitacora> listBitacora(Integer id) {
        
    return bitacoraDao.listBitacora(id);
    
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TBitacora bitacora) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
