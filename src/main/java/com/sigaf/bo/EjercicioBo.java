/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bo;

import com.sigaf.Ibo.IEjercicioBo;
import com.sigaf.Idao.IEjercicioDao;

import com.sigaf.pojo.TEjercicio;
import java.util.List;

/**
 *
 * @author Eliseo
 */
public class EjercicioBo implements IEjercicioBo{
   
    private IEjercicioDao ejercicioDao;
    

    public IEjercicioDao getEjercicioDao() {
        return ejercicioDao;
    }

    public void setEjercicioDao(IEjercicioDao ejercicioDao) {
        this.ejercicioDao = ejercicioDao;
    }
    
    @Override
    public void create(TEjercicio ejercicio) {
    
    ejercicioDao.create(ejercicio);
    
    }

    @Override
    public TEjercicio getEjercicio(Integer id) {
    return ejercicioDao.getEjercicio(id);
    }

    @Override
    public List<TEjercicio> listEjercicio(Integer id) {
    return ejercicioDao.listEjercicio(id);
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(TEjercicio ejercicio) {
    this.ejercicioDao.update(ejercicio);
    }

    @Override
    public TEjercicio getEjercicioAbierto(Integer id) {
        
    return this.ejercicioDao.getEjercicioAbierto(id);
    
    }
    
    
}
