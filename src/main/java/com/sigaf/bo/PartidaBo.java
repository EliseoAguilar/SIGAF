/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IPartidaBo;
import com.sigaf.Idao.IPartidaDao;
import com.sigaf.pojo.TPartida;
import java.util.Date;
import java.util.List;



/**
 *
 * @author Eliseo
 */

public class PartidaBo implements IPartidaBo{
    
    private IPartidaDao partidaDao;

    public IPartidaDao getPartidaDao() {
        return partidaDao;
    }

    public void setPartidaDao(IPartidaDao partidaDao) {
        this.partidaDao = partidaDao;
    }
    
    

    @Override
    public void create(TPartida partida) {
        
        this.partidaDao.create(partida);
    
    }

    @Override
    public TPartida getPartida(Integer id) {
       return this.partidaDao.getPartida(id);
    }

    @Override
    public List<TPartida> listPartida(Integer id) {
        return this.partidaDao.listPartida(id) ;
    
    }

    @Override
    public void delete(TPartida partida, Integer id) {
         this.partidaDao.delete(partida,id);
         }

    @Override
    public void update(TPartida partida) {
    this.partidaDao.update(partida);
    }
    
    @Override
    public Integer numeroPartida(Integer id){
    return this.partidaDao.numeroPartida(id);
    }
}
