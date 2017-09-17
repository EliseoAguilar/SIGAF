/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IDetalleParidaBo;
import com.sigaf.Idao.IDetallePartidaDao;
import com.sigaf.pojo.TDetallePartida;
import java.util.List;



/**
 *
 * @author Eliseo
 */
public class DetallePartidaBo implements IDetalleParidaBo{
    
    private IDetallePartidaDao detallePartidaDao ;

    public IDetallePartidaDao getDetallePartidaDao() {
        return detallePartidaDao;
    }

    public void setDetallePartidaDao(IDetallePartidaDao detallePartidaDao) {
        this.detallePartidaDao = detallePartidaDao;
    }

   
    
    

    @Override
    public void create(TDetallePartida detallePartida) {
    detallePartidaDao.create(detallePartida);
    }

    @Override
    public TDetallePartida getDetallePartida(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TDetallePartida> listDetallePartida(Integer id) {
    return this.detallePartidaDao.listDetallePartida(id);
    }

    @Override
    public void delete(Integer id) {
    this.detallePartidaDao.delete(id);    
    }

    @Override
    public void update(TDetallePartida detallePartida) {
        this.detallePartidaDao.update(detallePartida);  }
    
}
